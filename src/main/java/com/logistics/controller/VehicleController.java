package com.logistics.controller;

import com.logistics.dto.VehicleDTO;
import com.logistics.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "车辆管理", description = "车辆信息的增删改查")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "获取所有车辆列表")
    public ResponseEntity<Page<VehicleDTO>> getAllVehicles(Pageable pageable) {
        return ResponseEntity.ok(vehicleService.getAllVehicles(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取车辆详情")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PostMapping
    @Operation(summary = "创建新车辆")
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO savedVehicle = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新车辆信息")
    public ResponseEntity<VehicleDTO> updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除车辆")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "搜索车辆")
    public ResponseEntity<List<VehicleDTO>> searchVehicles(@RequestParam String licensePlate) {
        return ResponseEntity.ok(vehicleService.searchVehicles(licensePlate));
    }

    @GetMapping("/warehouse/{warehouseId}")
    @Operation(summary = "获取仓库下的车辆")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByWarehouse(warehouseId));
    }
}