package com.logistics.dto.transport;

import com.logistics.model.DeliveryTask;
import lombok.Data;

@Data
public class DeliveryTaskDTO {
    private Long taskId;
    private Long orderId;
    private Long vehicleId;
    private String licensePlate;
    private Long driverId;
    private String driverName;
    private Long warehouseId;
    private String warehouseLocation;
    private String toAddress;
    private Double distance;
    private Double freightCost;
    private String status;

    public DeliveryTaskDTO(DeliveryTask task) {
        this.taskId = task.getTaskId();
        this.orderId = task.getOrders().getOrderId();

        if (task.getVehicle() != null) {
            this.vehicleId = task.getVehicle().getVehicleId();
            this.licensePlate = task.getVehicle().getLicensePlate();
        }

        if (task.getDriver() != null) {
            this.driverId = task.getDriver().getDriverId();
            this.driverName = task.getDriver().getName();
        }

        if (task.getFromWarehouse() != null) {
            this.warehouseId = task.getFromWarehouse().getWarehouseId();
            this.warehouseLocation = task.getFromWarehouse().getLocation();
        }

        this.toAddress = task.getToAddress();
        this.distance = task.getDistance();
        this.freightCost = task.getFreightCost();
        this.status = task.getStatus().name();
    }
}