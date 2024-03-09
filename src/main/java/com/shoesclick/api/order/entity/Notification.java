package com.shoesclick.api.order.entity;

import com.shoesclick.api.order.enums.TypeTemplate;

public class Notification {

    private Long idOrder;

    private Long idCustomer;

    private TypeTemplate typeTemplate;

    public Long getIdOrder() {
        return idOrder;
    }

    public Notification setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public Notification setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public TypeTemplate getTypeTemplate() {
        return typeTemplate;
    }

    public Notification setTypeTemplate(TypeTemplate typeTemplate) {
        this.typeTemplate = typeTemplate;
        return this;
    }
}
