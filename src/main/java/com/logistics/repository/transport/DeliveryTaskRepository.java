package com.logistics.repository.transport;

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

    // 根据状态查找运输任务
    List<DeliveryTask> findByStatus(DeliveryStatus status);

    // 根据订单ID查找运输任务
    Optional<DeliveryTask> findByOrderId(Long orderId);

    // 根据驾驶员ID查找运输任务
    List<DeliveryTask> findByDriverId(Long driverId);

    // 根据车辆ID查找运输任务
    List<DeliveryTask> findByVehicleId(Long vehicleId);

    // 根据仓库ID查找运输任务
    List<DeliveryTask> findByFromWarehouseId(Long warehouseId);
}