package com.logistics.dto.order;

import com.logistics.dto.transport.DeliveryTaskDTO;
import com.logistics.model.Orders;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long customerId;
    private String customerName;
    private LocalDateTime createTime;
    private String shippingAddress;
    private String status;
    private List<OrderItemResponseDTO> items;
    private DeliveryTaskDTO deliveryTask;

    public OrderResponseDTO(Orders orders) {
        this.orderId = orders.getOrderId();
        this.customerId = orders.getCustomer().getCustomerId();
        this.customerName = orders.getCustomer().getName();
        this.createTime = orders.getCreateTime();
        this.shippingAddress = orders.getShippingAddress();
        this.status = orders.getStatus().name();

        if (orders.getOrderItems() != null) {
            this.items = orders.getOrderItems().stream()
                    .map(OrderItemResponseDTO::new)
                    .collect(Collectors.toList());
        }

        if (orders.getDeliveryTask() != null) {
            this.deliveryTask = new DeliveryTaskDTO(orders.getDeliveryTask());
        }
    }
}