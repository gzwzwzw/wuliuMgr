package com.logistics.repository.info;

import com.logistics.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // 根据名称模糊查询驾驶员
    @Query("SELECT d FROM Driver d WHERE d.name LIKE %:name%")
    List<Driver> findByNameContaining(@Param("name") String name);

    // 根据车辆ID查找驾驶员
    Optional<Driver> findByVehicleId(Long vehicleId);

    // 根据联系方式查询驾驶员
    List<Driver> findByContactInfo(String contactInfo);

    // 根据出生日期范围查询驾驶员
    List<Driver> findByBirthDateBetween(String startDate, String endDate);
}