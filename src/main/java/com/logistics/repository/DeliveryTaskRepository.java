package com.logistics.repository;

import com.logistics.model.DeliveryTask;
import com.logistics.model.DeliveryTask.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryTaskRepository extends JpaRepository<DeliveryTask, Long> {

    @Query("SELECT dt FROM DeliveryTask dt WHERE dt.status = :status")
    List<DeliveryTask> findByStatus(@Param("status") DeliveryStatus status);

    @Query("SELECT dt FROM DeliveryTask dt WHERE dt.order.orderId = :orderId")
    Optional<DeliveryTask> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT dt FROM DeliveryTask dt WHERE dt.driver.driverId = :driverId")
    List<DeliveryTask> findByDriverId(@Param("driverId") Long driverId);
}