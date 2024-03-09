package com.shoesclick.api.order.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public class MqProperties {

    private String exchange;

    private MQPaymentProperties payment;

    private MQNotificationProperties notification;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public MQPaymentProperties getPayment() {
        return payment;
    }

    public void setPayment(MQPaymentProperties payment) {
        this.payment = payment;
    }

    public MQNotificationProperties getNotification() {
        return notification;
    }

    public void setNotification(MQNotificationProperties notification) {
        this.notification = notification;
    }
}
