package com.shoesclick.api.order.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="`order`")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status = 1;

    private Long idCustomer;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();


    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Order setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public Order setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Order setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Order setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
