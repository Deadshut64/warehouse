package com.example.warehouse.aop;

import com.example.warehouse.aop.model.ErrorMessage;
import com.example.warehouse.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<ErrorMessage> handleNotOrder(OrderNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<ErrorMessage> handleAll(Throwable ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
