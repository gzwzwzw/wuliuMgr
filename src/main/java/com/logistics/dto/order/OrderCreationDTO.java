package com.logistics.dto.order;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.List;

@Data
public class OrderCreationDTO {
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @NotBlank(message = "收货地址不能为空")
    private String shippingAddress;

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemDTO> items;
}