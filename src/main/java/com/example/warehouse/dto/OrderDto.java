package com.example.warehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Модель для запроса создания заказа")
public class OrderDto {

    @NotBlank(message = "Клиент не может быть пустым")
    @Schema(description = "Данные по клиенту",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Иванов Иван")
    private String client;

    @NotNull(message = "Сумма не может быть null")
    @DecimalMin(value = "0.00", message = "Сумма должна быть положительной")
    @Schema(description = "Стоимость заказа",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "100.0")
    private BigDecimal sum;
}
