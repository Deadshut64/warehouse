package com.example.warehouse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The enum Order status.
 */
@Getter
public enum Status {

    CREATED("Создан"),
    SENT("Отправлен"),
    COMPLETED("Завершен"),
    CANCELED("Отменен");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
