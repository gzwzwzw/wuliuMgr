package com.logistics.dto.order;

import com.logistics.model.OrderItem;
import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Long itemId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Long warehouseId;
    private String warehouseLocation;

    public OrderItemResponseDTO(OrderItem orderItem) {
        this.itemId = orderItem.getItemId();
        this.productId = orderItem.getProduct().getProductId();
        this.productName = orderItem.getProduct().getName();
        this.quantity = orderItem.getQuantity();

        if (orderItem.getWarehouse() != null) {
            this.warehouseId = orderItem.getWarehouse().getWarehouseId();
            this.warehouseLocation = orderItem.getWarehouse().getLocation();
        }
    }
}