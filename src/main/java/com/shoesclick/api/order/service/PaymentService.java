package com.shoesclick.api.order.service;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.api.order.mapper.PaymentMapper;
import com.shoesclick.payment.avro.PaymentAvro;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
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
        kafkaTemplate.send(kafkaProperties.payment().topic(), String.valueOf(order.getId()) ,  paymentMapper.map(getPayment(order,paymentDomain)) );
    }

    private Payment getPayment(Order order, PaymentDomain paymentDomain) {
        return new Payment()
                .setOrder(order)
                .setPaymentType(paymentDomain.getPaymentType())
                .setPaymentParams(paymentDomain.getPaymentParams());
    }
}
