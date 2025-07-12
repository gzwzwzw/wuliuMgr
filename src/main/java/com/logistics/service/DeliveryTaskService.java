package com.logistics.service;

import com.logistics.model.DeliveryTask;
import com.logistics.repository.DeliveryTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryTaskService {
    private final DeliveryTaskRepository deliveryTaskRepository;

    @Transactional
    public DeliveryTask createDeliveryTask(DeliveryTask task) {
        return deliveryTaskRepository.save(task);
    }

    public List<DeliveryTask> getAllTasks() {
        return deliveryTaskRepository.findAll();
    }

    @Transactional
    public DeliveryTask updateTaskStatus(Long taskId, DeliveryTask.DeliveryStatus status) {
        DeliveryTask task = deliveryTaskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Delivery task not found"));
        task.setStatus(status);
        return deliveryTaskRepository.save(task);
    }

    public List<DeliveryTask> getTasksByStatus(DeliveryTask.DeliveryStatus status) {
        return deliveryTaskRepository.findByStatus(status);
    }
}