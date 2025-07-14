package com.logistics.service.info;

import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.Driver;
import com.logistics.model.Vehicle;
import com.logistics.model.Warehouse;
import com.logistics.repository.info.DriverRepository;
import com.logistics.repository.info.VehicleRepository;
import com.logistics.repository.warehouse.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    public VehicleService(VehicleRepository vehicleRepository, WarehouseRepository warehouseRepository, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.warehouseRepository = warehouseRepository;
        this.driverRepository = driverRepository;
    }

    private final VehicleRepository vehicleRepository;
    private final WarehouseRepository warehouseRepository;
    private final DriverRepository driverRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("车辆不存在: " + vehicleId));
    }

    public List<Vehicle> searchVehicles(String licensePlate) {
        return vehicleRepository.findByLicensePlateContaining(licensePlate);
    }

    @Transactional
    public Vehicle createVehicle(Vehicle vehicle) {
        // 设置仓库
        if (vehicle.getWarehouse() != null && vehicle.getWarehouse().getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(vehicle.getWarehouse().getWarehouseId())
                    .orElseThrow(() -> new ResourceNotFoundException("仓库不存在: " + vehicle.getWarehouse().getWarehouseId()));
            vehicle.setWarehouse(warehouse);
        }

        // 设置驾驶员
        if (vehicle.getDriver() != null && vehicle.getDriver().getDriverId() != null) {
            handleDriverAssociation(vehicle, vehicle.getDriver().getDriverId());
        }

        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle updateVehicle(Vehicle vehicleDetails, Long vehicleId) {
        Vehicle vehicle = getVehicleById(vehicleId);
        vehicle.setLicensePlate(vehicleDetails.getLicensePlate());

        // 更新仓库关联
        if (vehicleDetails.getWarehouse() != null && vehicleDetails.getWarehouse().getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(vehicleDetails.getWarehouse().getWarehouseId())
                    .orElseThrow(() -> new ResourceNotFoundException("仓库不存在: " + vehicleDetails.getWarehouse().getWarehouseId()));
            vehicle.setWarehouse(warehouse);
        } else {
            vehicle.setWarehouse(null);
        }

        // 更新驾驶员关联
        Long driverId = (vehicleDetails.getDriver() != null) ? vehicleDetails.getDriver().getDriverId() : null;
        handleDriverAssociation(vehicle, driverId);

        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = getVehicleById(vehicleId);

        // 解除与驾驶员的关联
        if (vehicle.getDriver() != null) {
            Driver driver = vehicle.getDriver();
            driver.setVehicle(null);
            driverRepository.save(driver);
        }

        vehicleRepository.delete(vehicle);
    }

    private void handleDriverAssociation(Vehicle vehicle, Long newDriverId) {
        // 当前分配的驾驶员
        Driver currentDriver = vehicle.getDriver();

        // 新指定的驾驶员
        Driver newDriver = null;
        if (newDriverId != null) {
            newDriver = driverRepository.findById(newDriverId)
                    .orElseThrow(() -> new ResourceNotFoundException("驾驶员不存在: " + newDriverId));
        }

        // 情况1: 解除当前驾驶员关联
        if (currentDriver != null && !currentDriver.equals(newDriver)) {
            currentDriver.setVehicle(null);
            driverRepository.save(currentDriver);
            vehicle.setDriver(null);
        }

        // 情况2: 分配新驾驶员
        if (newDriver != null && !newDriver.equals(currentDriver)) {
            // 检查新驾驶员是否已有车辆
            if (newDriver.getVehicle() != null && !newDriver.getVehicle().equals(vehicle)) {
                throw new ResourceNotFoundException("驾驶员 " + newDriver.getName() + " 已分配车辆: " + newDriver.getVehicle().getLicensePlate());
            }

            vehicle.setDriver(newDriver);
            newDriver.setVehicle(vehicle);
            driverRepository.save(newDriver);
        }
    }
}