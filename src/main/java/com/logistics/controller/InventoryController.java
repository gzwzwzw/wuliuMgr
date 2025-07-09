package com.logistics.controller;

import com.logistics.dto.InventoryDTO;
import com.logistics.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@Tag(name = "库存管理", description = "库存信息的查询和更新")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @Operation(summary = "查询库存")
    public ResponseEntity<List<InventoryDTO>> getInventories(
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Long productId) {
        return ResponseEntity.ok(inventoryService.getInventories(warehouseId, productId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    @Operation(summary = "获取仓库库存")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(inventoryService.getInventoriesByWarehouse(warehouseId));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "获取商品库存")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getInventoriesByProduct(productId));
    }

    @GetMapping("/warehouse-product")
    @Operation(summary = "获取特定仓库和商品的库存")
    public ResponseEntity<InventoryDTO> getInventoryByWarehouseAndProduct(
            @RequestParam Long warehouseId,
            @RequestParam Long productId) {
        return ResponseEntity.ok(inventoryService.getInventoryByWarehouseAndProduct(warehouseId, productId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新库存数量")
    public ResponseEntity<InventoryDTO> updateInventoryQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.updateInventoryQuantity(id, quantity));
    }

    @PostMapping
    @Operation(summary = "创建库存记录")
    public ResponseEntity<InventoryDTO> createInventory(@Valid @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO savedInventory = inventoryService.createInventory(inventoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
    }
}