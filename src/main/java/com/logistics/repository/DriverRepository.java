package com.logistics.repository;

import com.logistics.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT d FROM Driver d WHERE d.name LIKE %:name%")
    List<Driver> findByNameContaining(@Param("name") String name);

    @Query("SELECT d FROM Driver d WHERE d.vehicle.vehicleId = :vehicleId")
    Optional<Driver> findByVehicleId(@Param("vehicleId") Long vehicleId);
}