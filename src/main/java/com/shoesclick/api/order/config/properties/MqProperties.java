package com.shoesclick.api.order.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record MqProperties(String exchange, MQPaymentProperties payment, MQNotificationProperties notification) {}
