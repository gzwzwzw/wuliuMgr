package com.logistics.controller;

import com.logistics.dto.DriverDTO;
import com.logistics.service.DriverService;
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
@RequestMapping("/api/drivers")
@Tag(name = "司机管理", description = "司机信息的增删改查")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    @Operation(summary = "获取所有司机列表")
    public ResponseEntity<Page<DriverDTO>> getAllDrivers(Pageable pageable) {
        return ResponseEntity.ok(driverService.getAllDrivers(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取司机详情")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PostMapping
    @Operation(summary = "创建新司机")
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        DriverDTO savedDriver = driverService.createDriver(driverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDriver);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新司机信息")
    public ResponseEntity<DriverDTO> updateDriver(
            @PathVariable Long id,
            @Valid @RequestBody DriverDTO driverDTO) {
        return ResponseEntity.ok(driverService.updateDriver(id, driverDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除司机")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "搜索司机")
    public ResponseEntity<List<DriverDTO>> searchDrivers(@RequestParam String name) {
        return ResponseEntity.ok(driverService.searchDrivers(name));
    }

    @PutMapping("/{driverId}/assign-vehicle")
    @Operation(summary = "分配车辆给司机")
    public ResponseEntity<DriverDTO> assignVehicleToDriver(
            @PathVariable Long driverId,
            @RequestParam Long vehicleId) {
        return ResponseEntity.ok(driverService.assignVehicleToDriver(driverId, vehicleId));
    }

    @PutMapping("/{driverId}/unassign-vehicle")
    @Operation(summary = "取消司机车辆分配")
    public ResponseEntity<DriverDTO> unassignVehicleFromDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.unassignVehicleFromDriver(driverId));
    }
}