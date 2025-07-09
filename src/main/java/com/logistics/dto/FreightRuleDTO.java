package com.logistics.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FreightRuleDTO {
    private Long ruleId;

    @NotNull(message = "最小距离不能为空")
    @DecimalMin(value = "0.0", message = "最小距离不能小于0")
    private Double minDistance;

    @NotNull(message = "最大距离不能为空")
    @DecimalMin(value = "0.0", message = "最大距离不能小于0")
    private Double maxDistance;

    @NotNull(message = "单价不能为空")
    @DecimalMin(value = "0.0", message = "单价不能小于0")
    private Double unitPrice;
}