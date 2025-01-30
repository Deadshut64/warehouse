package com.example.warehouse.producer;

import com.example.warehouse.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Класс Kafka producer для отправки сообщений.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${topic.name}")
    private String topicName;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    /**
     * Отпавляем данные пользователя в Kafka.
     *
     * @param order the order dto
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String sendMessage(Order order) throws JsonProcessingException {
        log.info("{} Налало отправки сообшения", this.getClass());

        byte[] orderAsMessage = objectMapper.writeValueAsBytes(order);
        kafkaTemplate.send(topicName, orderAsMessage);

        log.info("order отправлен");

        return "Сообщение отправлено";
    }
}
