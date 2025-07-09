package com.logistics.repository;

import com.logistics.model.Order;
import com.logistics.model.Order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o " +
            "WHERE (:orderId IS NULL OR o.orderId = :orderId) " +
            "AND (:customerName IS NULL OR o.customer.name LIKE %:customerName%) " +
            "AND (:status IS NULL OR o.status = :status) " +
            "AND (:startDate IS NULL OR o.createTime >= :startDate) " +
            "AND (:endDate IS NULL OR o.createTime <= :endDate)")
    Page<Order> findByCriteria(@Param("orderId") Long orderId,
                               @Param("customerName") String customerName,
                               @Param("status") OrderStatus status,
                               @Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate,
                               Pageable pageable);

    default Page<Order> findByCriteria(Long orderId, String customerName, String status, Pageable pageable) {
        OrderStatus orderStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                orderStatus = OrderStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                // 如果状态无效，则忽略该条件
            }
        }
        return findByCriteria(orderId, customerName, orderStatus, null, null, pageable);
    }
}