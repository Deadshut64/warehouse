package com.example.warehouse.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Order status.
 */

@Getter
@RequiredArgsConstructor
@Schema(description = "Статус заказа")
public enum Status {

    CREATED("Создан"),
    SENT("Отправлен"),
    COMPLETED("Завершен"),
    CANCELED("Отменен");

    private final String description;
}
