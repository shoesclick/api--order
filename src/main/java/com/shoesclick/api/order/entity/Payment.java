package com.shoesclick.api.order.entity;

import java.util.Map;

public class Payment {


    private Order order;

    private String paymentType;

    private Map<String, Object> paymentParams;

    public Order getOrder() {
        return order;
    }

    public Payment setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Payment setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Map<String, Object> getPaymentParams() {
        return paymentParams;
    }

    public Payment setPaymentParams(Map<String, Object> paymentParams) {
        this.paymentParams = paymentParams;
        return this;
    }
}
