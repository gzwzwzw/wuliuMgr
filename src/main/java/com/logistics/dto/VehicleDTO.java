package com.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleDTO {
    private Long vehicleId;

    @NotBlank(message = "车牌号不能为空")
    @Size(max = 20, message = "车牌号不能超过20个字符")
    private String licensePlate;

    @NotNull(message = "所属仓库不能为空")
    private Long warehouseId;
}