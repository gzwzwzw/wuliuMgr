package com.logistics.controller;

import com.logistics.dto.OrderItemDTO;
import com.logistics.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@Tag(name = "订单项管理", description = "订单项的增删改查")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "获取订单的所有订单项")
    public ResponseEntity<List<OrderItemDTO>> getOrderItemsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getOrderItemsByOrder(orderId));
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "获取订单项详情")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long itemId) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(itemId));
    }

    @PostMapping
    @Operation(summary = "创建订单项")
    public ResponseEntity<OrderItemDTO> createOrderItem(@Valid @RequestBody OrderItemDTO orderItemDTO) {
        OrderItemDTO savedItem = orderItemService.createOrderItem(orderItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @PutMapping("/{itemId}")
    @Operation(summary = "更新订单项")
    public ResponseEntity<OrderItemDTO> updateOrderItem(
            @PathVariable Long itemId,
            @Valid @RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(itemId, orderItemDTO));
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "删除订单项")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long itemId) {
        orderItemService.deleteOrderItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{itemId}/assign-warehouse")
    @Operation(summary = "为订单项分配仓库")
    public ResponseEntity<OrderItemDTO> assignWarehouseToOrderItem(
            @PathVariable Long itemId,
            @RequestParam Long warehouseId) {
        return ResponseEntity.ok(orderItemService.assignWarehouseToOrderItem(itemId, warehouseId));
    }
}