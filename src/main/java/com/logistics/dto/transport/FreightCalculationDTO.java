package com.logistics.dto.transport;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class FreightCalculationDTO {
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @NotNull(message = "距离不能为空")
    @Positive(message = "距离必须大于0")
    private Double distance;
}