package com.logistics.controller;

import com.logistics.dto.WarehouseDTO;
import com.logistics.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
@Tag(name = "仓库管理", description = "仓库信息的增删改查")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    @Operation(summary = "获取所有仓库列表")
    public ResponseEntity<Page<WarehouseDTO>> getAllWarehouses(Pageable pageable) {
        return ResponseEntity.ok(warehouseService.getAllWarehouses(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取仓库详情")
    public ResponseEntity<WarehouseDTO> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    @PostMapping
    @Operation(summary = "创建新仓库")
    public ResponseEntity<WarehouseDTO> createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        WarehouseDTO savedWarehouse = warehouseService.createWarehouse(warehouseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWarehouse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新仓库信息")
    public ResponseEntity<WarehouseDTO> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseDTO warehouseDTO) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除仓库")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "搜索仓库")
    public ResponseEntity<List<WarehouseDTO>> searchWarehouses(@RequestParam String location) {
        return ResponseEntity.ok(warehouseService.searchWarehouses(location));
    }

    @GetMapping("/{warehouseId}/inventories")
    @Operation(summary = "获取仓库库存")
    public ResponseEntity<List<InventoryDTO>> getWarehouseInventories(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(warehouseService.getWarehouseInventories(warehouseId));
    }

    @GetMapping("/with-stock")
    @Operation(summary = "查询有库存的仓库")
    public ResponseEntity<List<WarehouseDTO>> getWarehousesWithStock(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(warehouseService.getWarehousesWithStock(productId, quantity));
    }
}