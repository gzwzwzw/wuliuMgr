package com.logistics.dto;

import com.logistics.model.DeliveryTask.DeliveryStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeliveryTaskDTO {
    private Long taskId;

    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @NotNull(message = "车辆ID不能为空")
    private Long vehicleId;

    @NotNull(message = "司机ID不能为空")
    private Long driverId;

    @NotNull(message = "起始仓库ID不能为空")
    private Long fromWarehouseId;

    @NotNull(message = "收货地址不能为空")
    private String toAddress;

    private Double distance;

    private Double freightCost;

    private DeliveryStatus status;
}