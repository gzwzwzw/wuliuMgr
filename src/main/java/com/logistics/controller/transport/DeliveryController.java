package com.logistics.controller.transport;

import com.logistics.dto.transport.DeliveryTaskDTO;
import com.logistics.dto.transport.DeliveryStatusUpdateDTO;
import com.logistics.dto.transport.FreightCalculationDTO;
import com.logistics.model.DeliveryTask;
import com.logistics.service.transport.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    private final DeliveryService deliveryService;

    @GetMapping("/tasks")
    public ResponseEntity<List<DeliveryTaskDTO>> getAllDeliveryTasks() {
        List<DeliveryTask> tasks = deliveryService.getAllDeliveryTasks();

        List<DeliveryTaskDTO> dtos = tasks.stream()
                .map(DeliveryTaskDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<DeliveryTaskDTO> getDeliveryTaskById(@PathVariable Long taskId) {
        DeliveryTask task = deliveryService.getDeliveryTaskById(taskId);
        return ResponseEntity.ok(new DeliveryTaskDTO(task));
    }

    @PostMapping("/calculate-freight")
    public ResponseEntity<Double> calculateFreight(@RequestBody FreightCalculationDTO calculationDTO) {
        double freightCost = deliveryService.calculateFreight(calculationDTO.getDistance());
        return ResponseEntity.ok(freightCost);
    }

    @PutMapping("/tasks/{taskId}/status")
    public ResponseEntity<DeliveryTaskDTO> updateDeliveryStatus(
            @PathVariable Long taskId,
            @RequestBody DeliveryStatusUpdateDTO updateDTO) {

        DeliveryTask updatedTask = deliveryService.updateDeliveryStatus(taskId, updateDTO.getNewStatus());
        return ResponseEntity.ok(new DeliveryTaskDTO(updatedTask));
    }

    @GetMapping("/tasks/order/{orderId}")
    public ResponseEntity<DeliveryTaskDTO> getDeliveryTaskByOrderId(@PathVariable Long orderId) {
        DeliveryTask task = deliveryService.getDeliveryTaskByOrderId(orderId);
        return ResponseEntity.ok(new DeliveryTaskDTO(task));
    }
}