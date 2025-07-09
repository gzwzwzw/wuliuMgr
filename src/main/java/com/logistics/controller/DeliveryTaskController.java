package com.logistics.controller;

import com.logistics.dto.DeliveryTaskDTO;
import com.logistics.service.DeliveryTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-tasks")
@Tag(name = "配送任务管理", description = "配送任务的增删改查")
public class DeliveryTaskController {

    private final DeliveryTaskService deliveryTaskService;

    public DeliveryTaskController(DeliveryTaskService deliveryTaskService) {
        this.deliveryTaskService = deliveryTaskService;
    }

    @GetMapping
    @Operation(summary = "获取配送任务列表")
    public ResponseEntity<List<DeliveryTaskDTO>> getDeliveryTasks(
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(deliveryTaskService.getDeliveryTasks(status));
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "根据订单ID获取配送任务")
    public ResponseEntity<DeliveryTaskDTO> getDeliveryTaskByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(deliveryTaskService.getDeliveryTaskByOrderId(orderId));
    }

    @GetMapping("/driver/{driverId}")
    @Operation(summary = "根据司机ID获取配送任务")
    public ResponseEntity<List<DeliveryTaskDTO>> getDeliveryTasksByDriverId(@PathVariable Long driverId) {
        return ResponseEntity.ok(deliveryTaskService.getDeliveryTasksByDriverId(driverId));
    }

    @PostMapping
    @Operation(summary = "创建配送任务")
    public ResponseEntity<DeliveryTaskDTO> createDeliveryTask(
            @Valid @RequestBody DeliveryTaskDTO deliveryTaskDTO) {
        DeliveryTaskDTO savedTask = deliveryTaskService.createDeliveryTask(deliveryTaskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新配送任务状态")
    public ResponseEntity<DeliveryTaskDTO> updateDeliveryTaskStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(deliveryTaskService.updateDeliveryTaskStatus(id, status));
    }
}