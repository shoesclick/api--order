package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.api.order.config.properties.KafkaServiceProperties;
import com.shoesclick.api.order.entity.Notification;
import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.api.order.enums.TypeTemplate;
import com.shoesclick.api.order.mapper.NotificationMapper;
import com.shoesclick.notification.avro.NotificationAvro;
import com.shoesclick.payment.avro.PaymentAvro;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class NotificationServiceTest {


    @Mock
    private KafkaTemplate<String, NotificationAvro> kafkaTemplate;

    @Mock
    private KafkaProperties kafkaProperties;

    @Mock
    private NotificationMapper notificationMapper;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private Jwt jwt;

    @InjectMocks
    private NotificationService notificationService;



    @BeforeEach
    public void setUp() {
        var kafkaNotficationProperties = new KafkaServiceProperties("GROUPID","TOPIC");
        when(kafkaProperties.bootstrapServers()).thenReturn("SERVER");
        when(kafkaProperties.schemaRegistry()).thenReturn("REGISTRY");
        when(kafkaProperties.notification()).thenReturn(kafkaNotficationProperties);
    }

    @Test
    void shouldSendNotificationSuccess() throws IllegalAccessException {
        try (MockedStatic mocked = mockStatic(SecurityContextHolder.class)) {

            mocked.when(()-> SecurityContextHolder.getContext()).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(jwt);
            when(jwt.getTokenValue()).thenReturn("TOKEN");

            when(notificationMapper.map(any(Notification.class))).thenReturn(new NotificationAvro());
            notificationService.sendNotification(new Notification().setIdOrder(1L).setIdCustomer(1L).setTypeTemplate(TypeTemplate.CREATE_ORDER));
            verify(kafkaTemplate, times(1)).send(any(ProducerRecord.class));


        }
    }
}
