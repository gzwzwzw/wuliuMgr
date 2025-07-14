package com.logistics.controller.info;

import com.logistics.dto.info.DriverDTO;
import com.logistics.model.Driver;
import com.logistics.service.info.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();

        List<DriverDTO> dtos = drivers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DriverDTO>> searchDrivers(@RequestParam String name) {
        List<Driver> drivers = driverService.searchDrivers(name);

        List<DriverDTO> dtos = drivers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        Driver savedDriver = driverService.createDriver(driver);
        return ResponseEntity.ok(convertToDTO(savedDriver));
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<DriverDTO> updateDriver(
            @PathVariable Long driverId,
            @RequestBody DriverDTO driverDTO) {

        Driver driver = convertToEntity(driverDTO);
        driver.setDriverId(driverId);
        Driver updatedDriver = driverService.updateDriver(driver, driverId);
        return ResponseEntity.ok(convertToDTO(updatedDriver));
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long driverId) {
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }

    private DriverDTO convertToDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();
        dto.setDriverId(driver.getDriverId());
        dto.setName(driver.getName());
        dto.setBirthDate(driver.getBirthDate());
        dto.setContactInfo(driver.getContactInfo());

        if (driver.getVehicle() != null) {
            dto.setVehicleId(driver.getVehicle().getVehicleId());
            dto.setLicensePlate(driver.getVehicle().getLicensePlate());
        }

        return dto;
    }

    private Driver convertToEntity(DriverDTO dto) {
        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setBirthDate(dto.getBirthDate());
        driver.setContactInfo(dto.getContactInfo());
        // 实际实现中需要设置vehicle
        return driver;
    }
}