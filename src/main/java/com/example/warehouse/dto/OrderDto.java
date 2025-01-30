package com.example.warehouse.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {

    @NotBlank(message = "Клиент не может быть пустым")
    private String client;

    @NotNull(message = "Сумма не может быть null")
    @DecimalMin(value = "0.00", message = "Сумма должна быть положительной")
    private BigDecimal sum;
}
