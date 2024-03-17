package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.api.order.config.properties.KafkaServiceProperties;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.api.order.mapper.PaymentMapper;
import com.shoesclick.payment.avro.PaymentAvro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {


    @Mock
    private KafkaTemplate<String, PaymentAvro> kafkaTemplate;

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private KafkaProperties kafkaProperties;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        var mqPaymentProperties = new KafkaServiceProperties("GROUPID","TOPIC");
        when(kafkaProperties.bootstrapServers()).thenReturn("SERVER");
        when(kafkaProperties.schemaRegistry()).thenReturn("REGISTRY");
        when(kafkaProperties.payment()).thenReturn(mqPaymentProperties);
    }

    @Test
    void shouldSendNotificationSuccess() throws IllegalAccessException {

        when(paymentMapper.map(anyMap())).thenReturn(new HashMap<>());
        when(paymentMapper.map(any(Payment.class))).thenReturn(new PaymentAvro());

        paymentService.sendPayment(new Order()
                .setId(1L)
                .setCreatedAt(LocalDateTime.now())
                .setStatus(1)
                .setIdCustomer(1L),
                new PaymentDomain().setPaymentType("PIX_PAYMENT").setPaymentParams(Map.of()));


        verify(kafkaTemplate, times(1)).send(anyString(), anyString(), any(PaymentAvro.class));
    }
}
