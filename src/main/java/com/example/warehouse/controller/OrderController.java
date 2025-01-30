package com.example.warehouse.controller;

import com.example.warehouse.dto.OrderDto;
import com.example.warehouse.exception.OrderSaveException;
import com.example.warehouse.model.Order;
import com.example.warehouse.model.Status;
import com.example.warehouse.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "Order контроллер", description = "Контроллер для запросов по заказам")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/new-order")
    @Operation(summary = "Запрос на создание заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Order.class)))
    })
    public ResponseEntity<OrderDto> createProduct(@RequestBody @Valid final OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.ok(orderDto);
    }

    @Operation(summary = "Получение данных по заказу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ObjectNotFoundException.class)))
    })
    @GetMapping("/get")
    public ResponseEntity<Object> getOrder(UUID id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @Operation(summary = "Запрос на изменение статуса заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderSaveException.class)))
    })
    @PostMapping("/change-status")
    public ResponseEntity<Object> changeStatusById(@RequestParam UUID id, @RequestParam Status status) {
        orderService.changeStatus(id, status);
        return ResponseEntity.ok("Заказ с id: " + id + " успешно обновлен, новый статус " + status);
    }

    @Operation(summary = "Запрос на удаление заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteOrderById(@RequestParam UUID id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Заказ с id: " + id + " удален");
    }
}
