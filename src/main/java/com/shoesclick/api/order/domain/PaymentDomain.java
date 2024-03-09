package com.shoesclick.api.order.domain;

import java.util.Map;

public class PaymentDomain {

    private String paymentType;

    private Map<String, Object> paymentParams;

    public String getPaymentType() {
        return paymentType;
    }

    public PaymentDomain setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Map<String, Object> getPaymentParams() {
        return paymentParams;
    }

    public PaymentDomain setPaymentParams(Map<String, Object> paymentParams) {
        this.paymentParams = paymentParams;
        return this;
    }
}
