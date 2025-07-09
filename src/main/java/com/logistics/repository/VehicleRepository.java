package com.logistics.repository;

import com.logistics.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.licensePlate LIKE %:licensePlate%")
    List<Vehicle> findByLicensePlateContaining(@Param("licensePlate") String licensePlate);

    @Query("SELECT v FROM Vehicle v WHERE v.warehouse.warehouseId = :warehouseId")
    List<Vehicle> findByWarehouseId(@Param("warehouseId") Long warehouseId);
}