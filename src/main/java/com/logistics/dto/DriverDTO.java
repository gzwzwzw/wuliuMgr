package com.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DriverDTO {
    private Long driverId;

    @NotBlank(message = "司机姓名不能为空")
    @Size(max = 100, message = "司机姓名不能超过100个字符")
    private String name;

    @NotBlank(message = "联系方式不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String contactInfo;

    private Long vehicleId;
}