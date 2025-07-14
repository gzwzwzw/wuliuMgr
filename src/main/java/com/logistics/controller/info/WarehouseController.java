package com.logistics.controller.info;

import com.logistics.dto.info.WarehouseDTO;
import com.logistics.model.Warehouse;
import com.logistics.service.warehouse.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();

        List<WarehouseDTO> dtos = warehouses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<WarehouseDTO>> searchWarehouses(@RequestParam String location) {
        List<Warehouse> warehouses = warehouseService.searchWarehouses(location);

        List<WarehouseDTO> dtos = warehouses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        Warehouse warehouse = convertToEntity(warehouseDTO);
        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.ok(convertToDTO(savedWarehouse));
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<WarehouseDTO> updateWarehouse(
            @PathVariable Long warehouseId,
            @RequestBody WarehouseDTO warehouseDTO) {

        Warehouse warehouse = convertToEntity(warehouseDTO);
        warehouse.setWarehouseId(warehouseId);
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse, warehouseId);
        return ResponseEntity.ok(convertToDTO(updatedWarehouse));
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.noContent().build();
    }

    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setWarehouseId(warehouse.getWarehouseId());
        dto.setLocation(warehouse.getLocation());
        return dto;
    }

    private Warehouse convertToEntity(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocation(dto.getLocation());
        return warehouse;
    }
}