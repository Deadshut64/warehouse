package com.example.warehouse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Order status.
 */
@Getter
@RequiredArgsConstructor
public enum Status {

    CREATED("Создан"),
    SENT("Отправлен"),
    COMPLETED("Завершен"),
    CANCELED("Отменен");

    private final String description;
}
