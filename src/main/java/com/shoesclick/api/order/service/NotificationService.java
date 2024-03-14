package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.api.order.entity.Notification;
import com.shoesclick.api.order.mapper.NotificationMapper;
import com.shoesclick.notification.avro.NotificationAvro;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class NotificationService {


    private final KafkaTemplate<String, NotificationAvro> kafkaTemplate;

    private final NotificationMapper notificationMapper;

    private final KafkaProperties kafkaProperties;

    public NotificationService(KafkaTemplate<String, NotificationAvro> kafkaTemplate, NotificationMapper notificationMapper, KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.notificationMapper = notificationMapper;
        this.kafkaProperties = kafkaProperties;
    }

    public void sendNotification(Notification notification) {
        kafkaTemplate.send(kafkaProperties.notification().topic(), notificationMapper.map(notification) );
    }
}
