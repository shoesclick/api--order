package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.MQNotificationProperties;
import com.shoesclick.api.order.config.properties.MqProperties;
import com.shoesclick.api.order.entity.Notification;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.enums.TypeTemplate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class NotificationServiceTest {

    @Mock
    private MqProperties mqProperties;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        var mqNotificationProperties = new MQNotificationProperties();
        mqNotificationProperties.setQueue("FILA");
        mqNotificationProperties.setRoutingKey("ROUTING");
        when(mqProperties.getExchange()).thenReturn("EXCHANGE");
        when(mqProperties.getNotification()).thenReturn(mqNotificationProperties);
    }

    @Test
    void shouldSendNotificationSuccess() throws IllegalAccessException {
        notificationService.sendNotification(new Notification().setIdOrder(1L).setIdCustomer(1L).setTypeTemplate(TypeTemplate.CREATE_ORDER));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }
}
