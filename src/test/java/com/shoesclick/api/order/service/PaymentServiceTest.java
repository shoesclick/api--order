package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.api.order.config.properties.KafkaServiceProperties;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.api.order.mapper.PaymentMapper;
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

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private Jwt jwt;

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

        try (MockedStatic mocked = mockStatic(SecurityContextHolder.class)) {

            mocked.when(()-> SecurityContextHolder.getContext()).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(jwt);
            when(jwt.getTokenValue()).thenReturn("TOKEN");


            when(paymentMapper.map(anyMap())).thenReturn(new HashMap<>());
            when(paymentMapper.map(any(Payment.class))).thenReturn(new PaymentAvro());

            paymentService.sendPayment(new Order()
                            .setId(1L)
                            .setCreatedAt(LocalDateTime.now())
                            .setStatus(1)
                            .setIdCustomer(1L),
                    new PaymentDomain().setPaymentType("PIX_PAYMENT").setPaymentParams(Map.of()));


            verify(kafkaTemplate, times(1)).send(any(ProducerRecord.class));


        }
    }
}
