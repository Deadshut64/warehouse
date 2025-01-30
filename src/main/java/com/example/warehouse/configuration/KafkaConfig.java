package com.example.warehouse.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс конфигурации для Kafka.
 */
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    /**
     * Возвращает экземпляр ProducerFactory<String, String>.
     *
     * @return the ProducerFactory
     */
    @Bean
    public ProducerFactory<String, byte[]> producerFactory() {

        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    /**
     * Возвращает экземпляр KafkaTemplate<String, String>.
     *
     * @return the KafkaTemplate
     */
    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Создает новый топик.
     *
     * @return the NewTopic
     */
    @Bean
    public NewTopic topic(@Value("${topic.name}")
                          String topicName) {
        return new NewTopic(topicName, 2, (short) 1);
    }
}
