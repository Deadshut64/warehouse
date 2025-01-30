package com.example.warehouse.listener;

import com.example.warehouse.exception.KafkaSendFailedException;
import com.example.warehouse.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Listener {

    @Value("${topic.dead}")
    private String topicDeadLetter;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "${topic.name}")
    public void receive(ConsumerRecord<String, byte[]> message) throws IOException {
        log.info("Сообщение получено {}", message);

        CompletableFuture.runAsync(() -> {
            try {
                Order order = objectMapper.readValue(message.value(), Order.class);
                log.info("Данные по заказу успешно прочитаны, order = {}", order);
            } catch (Exception e) {
                try {
                    kafkaTemplate.send(topicDeadLetter, message);
                    log.info("Заказ успешно отправлен на проверку: ");
                } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException exception) {
                    log.error("Ошибка при отправке сообщения в Kafka: ", exception);
                    throw new KafkaSendFailedException("Ошибка при отправке сообщения в Kafka", exception);
                }
            }
        });
    }
}
