package com.logistics.repository.info;

import com.logistics.model.Vehicle;
import com.logistics.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // 根据车牌号模糊查询车辆
    @Query("SELECT v FROM Vehicle v WHERE v.licensePlate LIKE %:licensePlate%")
    List<Vehicle> findByLicensePlateContaining(@Param("licensePlate") String licensePlate);

    // 根据仓库ID查找车辆
    List<Vehicle> findByWarehouseWarehouseId(Long warehouseId);

    // 根据驾驶员ID查找车辆
    Optional<Vehicle> findByDriverDriverId(Long driverId);

    // 查找没有驾驶员的车辆
    @Query("SELECT v FROM Vehicle v WHERE v.driver IS NULL")
    List<Vehicle> findAvailableVehicles();
}