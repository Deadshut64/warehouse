package com.example.warehouse.controller;

import com.example.warehouse.dto.OrderDto;
import com.example.warehouse.model.Status;
import com.example.warehouse.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new-order")
    public ResponseEntity<OrderDto> createProduct(@RequestBody @Valid final OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getOrder(UUID id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @PostMapping("/change-status")
    public ResponseEntity<Object> changeStatusById(@RequestParam UUID id, @RequestParam Status status) {
        orderService.changeStatus(id, status);
        return ResponseEntity.ok("Заказ с id: " + id + " успешно обновлен, новый статус " + status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteOrderById(@RequestParam UUID id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Заказ с id: " + id + " удален");
    }
}
