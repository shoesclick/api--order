package com.shoesclick.api.order.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "`order_item`")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProduct;

    private String nameProduct;

    private BigDecimal price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idOrder")
    private Order order;

    public Long getId() {
        return id;
    }

    public OrderItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public OrderItem setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderItem setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public OrderItem setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
