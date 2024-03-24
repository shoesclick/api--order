package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.api.order.mapper.PaymentMapper;
import com.shoesclick.notification.avro.NotificationAvro;
import com.shoesclick.payment.avro.PaymentAvro;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PaymentService {


    private final KafkaTemplate<String, PaymentAvro> kafkaTemplate;

    private final PaymentMapper paymentMapper;

    private final KafkaProperties kafkaProperties;

    public PaymentService(KafkaTemplate<String, PaymentAvro> kafkaTemplate, PaymentMapper paymentMapper, KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.paymentMapper = paymentMapper;
        this.kafkaProperties = kafkaProperties;
    }


    public void sendPayment(Order order, PaymentDomain paymentDomain) {
        var record = new ProducerRecord<>(kafkaProperties.payment().topic(), String.valueOf(order.getId()), paymentMapper.map(getPayment(order, paymentDomain)));
        kafkaTemplate.send(record);
    }

    private Payment getPayment(Order order, PaymentDomain paymentDomain) {
        return new Payment()
                .setOrder(order)
                .setPaymentType(paymentDomain.getPaymentType())
                .setPaymentParams(paymentDomain.getPaymentParams());
    }

}
