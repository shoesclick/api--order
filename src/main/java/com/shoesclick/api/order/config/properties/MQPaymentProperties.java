package com.shoesclick.api.order.config.properties;


public class MQPaymentProperties {

    private String routingKey;

    private String queue;

    public String getRoutingKey() {
        return routingKey;
    }

    public MQPaymentProperties setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
        return this;
    }

    public String getQueue() {
        return queue;
    }

    public MQPaymentProperties setQueue(String queue) {
        this.queue = queue;
        return this;
    }
}
