package com.logistics.repository.order;

import com.logistics.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // 根据订单ID查找订单项
    List<OrderItem> findByOrderOrderId(Long orderId);

    // 根据产品ID查找订单项
    List<OrderItem> findByProductProductId(Long productId);

    // 根据仓库ID查找订单项
    List<OrderItem> findByWarehouseWarehouseId(Long warehouseId);
}