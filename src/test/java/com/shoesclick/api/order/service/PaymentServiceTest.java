package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.MQNotificationProperties;
import com.shoesclick.api.order.config.properties.MQPaymentProperties;
import com.shoesclick.api.order.config.properties.MqProperties;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    @Mock
    private MqProperties mqProperties;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        var mqPaymentProperties = new MQPaymentProperties();
        mqPaymentProperties.setQueue("FILA");
        mqPaymentProperties.setRoutingKey("ROUTING");
        when(mqProperties.getExchange()).thenReturn("EXCHANGE");
        when(mqProperties.getPayment()).thenReturn(mqPaymentProperties);
    }

    @Test
    void shouldSendNotificationSuccess() throws IllegalAccessException {
        paymentService.sendPayment(new Order()
                .setId(1L)
                .setCreatedAt(LocalDateTime.now())
                .setStatus(1)
                .setIdCustomer(1L),
                new PaymentDomain().setPaymentType("PIX_PAYMENT").setPaymentParams(Map.of()));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }
}
