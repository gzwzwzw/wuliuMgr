package com.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WarehouseDTO {
    private Long warehouseId;

    @NotBlank(message = "仓库位置不能为空")
    @Size(max = 255, message = "仓库位置不能超过255个字符")
    private String location;
}