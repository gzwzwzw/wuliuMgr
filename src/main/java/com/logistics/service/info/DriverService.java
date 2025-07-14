package com.logistics.service.info;

import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.Driver;
import com.logistics.model.Vehicle;
import com.logistics.repository.info.DriverRepository;
import com.logistics.repository.info.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    public DriverService(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("驾驶员不存在: " + driverId));
    }

    public List<Driver> searchDrivers(String name) {
        return driverRepository.findByNameContaining(name);
    }

    @Transactional
    public Driver createDriver(Driver driver) {
        // 设置车辆关联
        if (driver.getVehicle() != null && driver.getVehicle().getVehicleId() != null) {
            handleVehicleAssociation(driver, driver.getVehicle().getVehicleId());
        }

        return driverRepository.save(driver);
    }

    @Transactional
    public Driver updateDriver(Driver driverDetails, Long driverId) {
        Driver driver = getDriverById(driverId);
        driver.setName(driverDetails.getName());
        driver.setBirthDate(driverDetails.getBirthDate());
        driver.setContactInfo(driverDetails.getContactInfo());

        // 更新车辆关联
        Long vehicleId = (driverDetails.getVehicle() != null) ? driverDetails.getVehicle().getVehicleId() : null;
        handleVehicleAssociation(driver, vehicleId);

        return driverRepository.save(driver);
    }

    @Transactional
    public void deleteDriver(Long driverId) {
        Driver driver = getDriverById(driverId);

        // 解除与车辆的关联
        if (driver.getVehicle() != null) {
            Vehicle vehicle = driver.getVehicle();
            vehicle.setDriver(null);
            vehicleRepository.save(vehicle);
        }

        driverRepository.delete(driver);
    }

    private void handleVehicleAssociation(Driver driver, Long newVehicleId) {
        // 当前分配的车辆
        Vehicle currentVehicle = driver.getVehicle();

        // 新指定的车辆
        Vehicle newVehicle = null;
        if (newVehicleId != null) {
            newVehicle = vehicleRepository.findById(newVehicleId)
                    .orElseThrow(() -> new ResourceNotFoundException("车辆不存在: " + newVehicleId));
        }

        // 情况1: 解除当前车辆关联
        if (currentVehicle != null && !currentVehicle.equals(newVehicle)) {
            currentVehicle.setDriver(null);
            vehicleRepository.save(currentVehicle);
            driver.setVehicle(null);
        }

        // 情况2: 分配新车辆
        if (newVehicle != null && !newVehicle.equals(currentVehicle)) {
            // 检查新车辆是否已被分配
            if (newVehicle.getDriver() != null && !newVehicle.getDriver().equals(driver)) {
                throw new ResourceNotFoundException("车辆 " + newVehicle.getLicensePlate() + " 已分配驾驶员: " + newVehicle.getDriver().getName());
            }

            driver.setVehicle(newVehicle);
            newVehicle.setDriver(driver);
            vehicleRepository.save(newVehicle);
        }
    }
}