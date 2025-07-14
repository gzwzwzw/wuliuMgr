package com.logistics.repository.order;

import com.logistics.model.Orders;
import com.logistics.model.Orders.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    // 根据客户ID查找订单
    List<Orders> findByCustomerCustomerId(Long customerId);

    // 根据状态查找订单
    List<Orders> findByStatus(OrderStatus status);

    // 复杂条件查询
    @Query("SELECT o FROM Orders o " +
            "WHERE (:orderId IS NULL OR o.orderId = :orderId) " +
            "AND (:customerName IS NULL OR o.customer.name LIKE %:customerName%) " +
            "AND (:status IS NULL OR o.status = :status) " +
            "AND (:startDate IS NULL OR o.createTime >= :startDate) " +
            "AND (:endDate IS NULL OR o.createTime <= :endDate)")
    Page<Orders> findByCriteria(
            @Param("orderId") Long orderId,
            @Param("customerName") String customerName,
            @Param("status") OrderStatus status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    // 根据创建时间范围查询
    List<Orders> findByCreateTimeBetween(LocalDateTime start, LocalDateTime end);
}