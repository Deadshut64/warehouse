package com.example.warehouse.exception;

public class OrderSaveException extends RuntimeException {

    public OrderSaveException(String message) {
        super(message);
    }
}
