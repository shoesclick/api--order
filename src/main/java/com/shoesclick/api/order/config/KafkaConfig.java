package com.shoesclick.api.order.config;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.notification.avro.NotificationAvro;
import com.shoesclick.payment.avro.PaymentAvro;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ProducerFactory<String, NotificationAvro>producerNotificationFactory () {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put( "schema.registry.url", kafkaProperties.schemaRegistry());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, PaymentAvro>producerPaymentFactory () {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put( "schema.registry.url", kafkaProperties.schemaRegistry());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, NotificationAvro> kafkaNotificationTemplate() {
        return new KafkaTemplate<>(producerNotificationFactory());
    }

    @Bean
    public KafkaTemplate<String, PaymentAvro> kafkaPaymentTemplate() {
        return new KafkaTemplate<>(producerPaymentFactory());
    }
}
