package com.logistics.controller.info;

import com.logistics.dto.info.VehicleDTO;
import com.logistics.model.Vehicle;
import com.logistics.service.info.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        List<VehicleDTO> dtos = vehicles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VehicleDTO>> searchVehicles(@RequestParam String licensePlate) {
        List<Vehicle> vehicles = vehicleService.searchVehicles(licensePlate);

        List<VehicleDTO> dtos = vehicles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(convertToDTO(savedVehicle));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDTO> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody VehicleDTO vehicleDTO) {

        Vehicle vehicle = convertToEntity(vehicleDTO);
        vehicle.setVehicleId(vehicleId);
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle, vehicleId);
        return ResponseEntity.ok(convertToDTO(updatedVehicle));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setLicensePlate(vehicle.getLicensePlate());

        if (vehicle.getWarehouse() != null) {
            dto.setWarehouseId(vehicle.getWarehouse().getWarehouseId());
            dto.setWarehouseLocation(vehicle.getWarehouse().getLocation());
        }

        if (vehicle.getDriver() != null) {
            dto.setDriverId(vehicle.getDriver().getDriverId());
            dto.setDriverName(vehicle.getDriver().getName());
        }

        return dto;
    }

    private Vehicle convertToEntity(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        // 实际实现中需要设置warehouse和driver
        return vehicle;
    }
}