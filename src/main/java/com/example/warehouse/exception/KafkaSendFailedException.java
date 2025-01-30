package com.example.warehouse.exception;

import org.apache.kafka.common.KafkaException;

public class KafkaSendFailedException extends KafkaException {
    public KafkaSendFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
