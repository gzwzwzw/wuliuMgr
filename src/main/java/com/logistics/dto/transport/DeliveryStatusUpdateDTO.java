package com.logistics.dto.transport;

import com.logistics.model.DeliveryTask;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class DeliveryStatusUpdateDTO {
    public DeliveryTask.DeliveryStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(DeliveryTask.DeliveryStatus newStatus) {
        this.newStatus = newStatus;
    }

    @NotNull(message = "新状态不能为空")
    private DeliveryTask.DeliveryStatus newStatus;
}