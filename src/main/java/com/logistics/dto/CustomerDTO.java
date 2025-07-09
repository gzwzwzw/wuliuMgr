package com.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long customerId;

    @NotBlank(message = "客户名称不能为空")
    @Size(max = 100, message = "客户名称不能超过100个字符")
    private String name;

    @NotBlank(message = "地址不能为空")
    @Size(max = 255, message = "地址不能超过255个字符")
    private String address;

    @Size(max = 100, message = "联系人姓名不能超过100个字符")
    private String contactPerson;
}