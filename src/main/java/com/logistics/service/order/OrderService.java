package com.logistics.service.order;

import com.logistics.dto.order.OrderCreationDTO;
import com.logistics.dto.order.OrderItemDTO;
import com.logistics.dto.order.OrderUpdateDTO;
import com.logistics.exception.InvalidOrderStateException;
import com.logistics.exception.ResourceNotFoundException;
import com.logistics.exception.InsufficientStockException;
import com.logistics.model.*;
import com.logistics.repository.info.CustomerRepository;
import com.logistics.repository.info.ProductRepository;
import com.logistics.repository.order.OrderItemRepository;
import com.logistics.repository.order.OrderRepository;
import com.logistics.repository.transport.DeliveryTaskRepository;
import com.logistics.repository.warehouse.InventoryRepository;
import com.logistics.repository.warehouse.WarehouseRepository;
import com.logistics.service.transport.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository, OrderItemRepository orderItemRepository, InventoryRepository inventoryRepository, DeliveryTaskRepository deliveryTaskRepository, DeliveryService deliveryService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.orderItemRepository = orderItemRepository;
        this.inventoryRepository = inventoryRepository;
        this.deliveryTaskRepository = deliveryTaskRepository;
        this.deliveryService = deliveryService;
    }

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final OrderItemRepository orderItemRepository;
    private final InventoryRepository inventoryRepository;
    private final DeliveryTaskRepository deliveryTaskRepository;
    private final DeliveryService deliveryService;

    @Transactional
    public Orders createOrder(OrderCreationDTO orderDTO) {
        // 验证客户
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("客户不存在: " + orderDTO.getCustomerId()));

        // 创建订单
        Orders orders = new Orders();
        orders.setCustomer(customer);
        orders.setCreateTime(LocalDateTime.now());
        orders.setShippingAddress(orderDTO.getShippingAddress());
        orders.setStatus(Orders.OrderStatus.PENDING);

        // 保存订单以生成ID
        Orders savedOrders = orderRepository.save(orders);

        // 处理订单项
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("商品不存在: " + itemDTO.getProductId()));

            // 选择仓库 - 优先选择距离最近的仓库
            Warehouse warehouse = selectWarehouseForProduct(
                    orderDTO.getShippingAddress(),
                    product.getProductId(),
                    itemDTO.getQuantity()
            ).orElseThrow(() -> new InsufficientStockException("没有足够库存的商品: " + product.getName()));

            // 创建订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(savedOrders);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setWarehouse(warehouse);

            // 预扣库存
            reserveStock(warehouse.getWarehouseId(), product.getProductId(), itemDTO.getQuantity());

            orderItems.add(orderItem);
        }

        // 保存订单项
        orderItemRepository.saveAll(orderItems);
        savedOrders.setOrderItems(orderItems);

        // 创建运输任务
        createDeliveryTask(savedOrders);

        return savedOrders;
    }

    @Transactional
    public Orders updateOrder(Long orderId, OrderUpdateDTO updateDTO) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("订单不存在: " + orderId));

        // 检查订单状态
        if (orders.getStatus() != Orders.OrderStatus.PENDING) {
            throw new InvalidOrderStateException("只有待处理状态的订单可以修改");
        }

        // 更新地址
        if (updateDTO.getShippingAddress() != null) {
            orders.setShippingAddress(updateDTO.getShippingAddress());
        }

        // 更新订单项
        if (updateDTO.getItems() != null && !updateDTO.getItems().isEmpty()) {
            // 返还原有库存
            for (OrderItem item : orders.getOrderItems()) {
                addStock(
                        item.getWarehouse().getWarehouseId(),
                        item.getProduct().getProductId(),
                        item.getQuantity()
                );
            }

            // 删除原有订单项
            orderItemRepository.deleteAll(orders.getOrderItems());
            orders.getOrderItems().clear();

            // 创建新订单项
            List<OrderItem> newItems = new ArrayList<>();
            for (OrderItemDTO itemDTO : updateDTO.getItems()) {
                Product product = productRepository.findById(itemDTO.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("商品不存在: " + itemDTO.getProductId()));

                // 选择仓库
                Warehouse warehouse = selectWarehouseForProduct(
                        orders.getShippingAddress(),
                        product.getProductId(),
                        itemDTO.getQuantity()
                ).orElseThrow(() -> new InsufficientStockException("没有足够库存的商品: " + product.getName()));

                OrderItem orderItem = new OrderItem();
                orderItem.setOrders(orders);
                orderItem.setProduct(product);
                orderItem.setQuantity(itemDTO.getQuantity());
                orderItem.setWarehouse(warehouse);

                // 预扣库存
                reserveStock(warehouse.getWarehouseId(), product.getProductId(), itemDTO.getQuantity());

                newItems.add(orderItem);
            }

            // 保存新订单项
            orderItemRepository.saveAll(newItems);
            orders.setOrderItems(newItems);
        }

        return orderRepository.save(orders);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("订单不存在: " + orderId));

        if (orders.getStatus() != Orders.OrderStatus.PENDING) {
            throw new InvalidOrderStateException("只有待处理状态的订单可以删除");
        }

        // 返还预扣库存
        for (OrderItem item : orders.getOrderItems()) {
            addStock(
                    item.getWarehouse().getWarehouseId(),
                    item.getProduct().getProductId(),
                    item.getQuantity()
            );
        }

        // 删除运输任务（如果存在）
        Optional<DeliveryTask> deliveryTask = deliveryTaskRepository.findByOrderId(orderId);
        deliveryTask.ifPresent(task -> deliveryTaskRepository.delete(task));

        orderRepository.delete(orders);
    }

    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("订单不存在: " + orderId));
    }

    public Page<Orders> searchOrders(Long orderId, String customerName, String status,
                                     LocalDateTime startDate, LocalDateTime endDate,
                                     Pageable pageable) {
        // 转换状态枚举
        Orders.OrderStatus orderStatus = null;
        if (status != null) {
            try {
                orderStatus = Orders.OrderStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                // 如果状态无效，则忽略该条件
            }
        }

        return orderRepository.findByCriteria(
                orderId, customerName, orderStatus, startDate, endDate, pageable
        );
    }

    private Optional<Warehouse> selectWarehouseForProduct(String shippingAddress, Long productId, int quantity) {
        // 1. 查找有足够库存的仓库
        List<Warehouse> warehouses = warehouseRepository.findWarehousesWithSufficientStock(productId, quantity);

        if (warehouses.isEmpty()) {
            return Optional.empty();
        }

        // 2. 简化逻辑：选择第一个仓库
        // 实际项目中应计算距离并选择最近的仓库
        return Optional.of(warehouses.get(0));
    }

    private void reserveStock(Long warehouseId, Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "仓库 " + warehouseId + " 中没有商品 " + productId + " 的库存记录"));

        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException(
                    "库存不足: 商品 " + productId + " 在仓库 " + warehouseId +
                            " 的库存为 " + inventory.getQuantity() + ", 需要 " + quantity);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }

    private void addStock(Long warehouseId, Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                .orElseGet(() -> {
                    Inventory newInventory = new Inventory();
                    newInventory.setWarehouseId(warehouseId);
                    newInventory.setProductId(productId);
                    newInventory.setQuantity(0);
                    return inventoryRepository.save(newInventory);
                });

        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventoryRepository.save(inventory);
    }

    private void createDeliveryTask(Orders orders) {
        // 简化处理：使用第一个订单项的仓库
        Warehouse warehouse = orders.getOrderItems().get(0).getWarehouse();

        // 选择仓库中的可用车辆
        List<Vehicle> vehicles = warehouse.getVehicles();
        if (vehicles.isEmpty()) {
            throw new ResourceNotFoundException("仓库 " + warehouse.getLocation() + " 中没有可用车辆");
        }

        Vehicle vehicle = vehicles.get(0);
        Driver driver = vehicle.getDriver();

        if (driver == null) {
            throw new ResourceNotFoundException("车辆 " + vehicle.getLicensePlate() + " 没有分配驾驶员");
        }

        // 计算距离（简化处理）
        double distance = calculateDistance(warehouse.getLocation(), orders.getShippingAddress());

        // 计算运费
        double freightCost = deliveryService.calculateFreight(distance);

        // 创建运输任务
        DeliveryTask deliveryTask = new DeliveryTask();
        deliveryTask.setOrders(orders);
        deliveryTask.setVehicle(vehicle);
        deliveryTask.setDriver(driver);
        deliveryTask.setFromWarehouse(warehouse);
        deliveryTask.setToAddress(orders.getShippingAddress());
        deliveryTask.setDistance(distance);
        deliveryTask.setFreightCost(freightCost);
        deliveryTask.setStatus(DeliveryTask.DeliveryStatus.PENDING);

        deliveryTaskRepository.save(deliveryTask);
        orders.setDeliveryTask(deliveryTask);
        orderRepository.save(orders);
    }

    private double calculateDistance(String fromLocation, String toAddress) {
        // 实际项目中应调用地图API计算距离
        // 这里使用随机值作为示例
        return 10 + (Math.random() * 100);
    }
}