package com.logistics.controller.warehouse;

import com.logistics.dto.warehouse.InventoryDTO;
import com.logistics.dto.warehouse.InventoryUpdateDTO;
import com.logistics.model.Inventory;
import com.logistics.service.warehouse.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    private final InventoryService inventoryService;

    @PostMapping("/add-stock")
    public ResponseEntity<InventoryDTO> addStock(@RequestBody InventoryUpdateDTO updateDTO) {
        Inventory inventory = inventoryService.addStock(
                updateDTO.getWarehouseId(),
                updateDTO.getProductId(),
                updateDTO.getQuantity()
        );
        return ResponseEntity.ok(convertToDTO(inventory));
    }

    @PostMapping("/reserve-stock")
    public ResponseEntity<InventoryDTO> reserveStock(@RequestBody InventoryUpdateDTO updateDTO) {
        Inventory inventory = inventoryService.reserveStock(
                updateDTO.getWarehouseId(),
                updateDTO.getProductId(),
                updateDTO.getQuantity()
        );
        return ResponseEntity.ok(convertToDTO(inventory));
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> searchInventory(
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) String productName) {

        List<Inventory> inventoryList = inventoryService.searchInventory(warehouseId, productId, productName);

        List<InventoryDTO> dtos = inventoryList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setWarehouseId(inventory.getWarehouse().getWarehouseId());
        dto.setWarehouseLocation(inventory.getWarehouse().getLocation());
        dto.setProductId(inventory.getProduct().getProductId());
        dto.setProductName(inventory.getProduct().getName());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }
}