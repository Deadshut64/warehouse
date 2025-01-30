package com.example.warehouse.service;

import com.example.warehouse.dto.OrderDto;
import com.example.warehouse.exception.OrderNotFoundException;
import com.example.warehouse.exception.OrderSaveException;
import com.example.warehouse.mapper.OrderMapper;
import com.example.warehouse.model.Order;
import com.example.warehouse.model.Status;
import com.example.warehouse.producer.KafkaProducer;
import com.example.warehouse.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final KafkaProducer kafkaProducer;

    public void createOrder(OrderDto orderDto) {
        orderRepository.save(OrderMapper.mapToOrderDto(orderDto));
    }

    public Order getOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Заказ с Id " + id + " не найден"));
    }

    @Transactional
    public void changeStatus(UUID id, Status status) {

        Order order = getOrder(id);
        order.setStatus(status);
        try {
            orderRepository.save(order);

            kafkaProducer.sendMessage(order);
        } catch (Exception e) {
            throw new OrderSaveException("Не удалось сохранить заказ в Базу");
        }
    }

    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
