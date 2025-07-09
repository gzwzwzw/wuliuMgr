package com.logistics.dto;

import com.logistics.model.Order.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;

    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    private LocalDateTime createTime;

    @NotBlank(message = "收货地址不能为空")
    @Size(max = 255, message = "收货地址不能超过255个字符")
    private String shippingAddress;

    private OrderStatus status;

    private List<OrderItemDTO> orderItems;
}

