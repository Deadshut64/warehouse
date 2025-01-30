package com.example.warehouse.exception;

import org.apache.kafka.common.KafkaException;

/**
 * Класс исключения при отправке в Kafka.
 */
public class KafkaSendFailedException extends KafkaException {
    public KafkaSendFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
