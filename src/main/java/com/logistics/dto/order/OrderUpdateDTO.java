package com.logistics.dto.order;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.List;

@Data
public class OrderUpdateDTO {
    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @NotBlank(message = "收货地址不能为空")
    private String shippingAddress;

    private List<OrderItemDTO> items;
}