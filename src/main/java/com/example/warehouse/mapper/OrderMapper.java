package com.example.warehouse.mapper;

import com.example.warehouse.dto.OrderDto;
import com.example.warehouse.model.Order;

public class OrderMapper {

    public static Order mapToOrderDto(OrderDto orderDto) {
        return Order.builder()
                .client(orderDto.getClient())
                .sum(orderDto.getSum())
                .build();
    }
}
