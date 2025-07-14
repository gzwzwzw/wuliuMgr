package com.logistics.dto.info;

import lombok.Data;

@Data
public class WarehouseDTO {
    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private Long warehouseId;
    private String location;
}