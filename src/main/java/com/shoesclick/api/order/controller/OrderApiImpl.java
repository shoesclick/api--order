package com.shoesclick.api.order.controller;

import com.shoesclick.api.order.mapper.OrderMapper;
import com.shoesclick.api.order.openapi.controller.OrderApi;
import com.shoesclick.api.order.openapi.model.domain.OrderRequest;
import com.shoesclick.api.order.openapi.model.domain.OrderResponse;
import com.shoesclick.api.order.openapi.model.domain.OrderStatusRequest;
import com.shoesclick.api.order.openapi.model.domain.StatusResponse;
import com.shoesclick.api.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderApiImpl implements OrderApi {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderApiImpl(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<OrderResponse> findById(Long id) {
        return ResponseEntity.ok(orderMapper.map(orderService.findById(id)));
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listByCustomer(Long idCustomer) {
        return ResponseEntity.ok(orderMapper.map(orderService.listOrderByCustomer(idCustomer)));
    }

    @Override
    public ResponseEntity<StatusResponse> save(OrderRequest orderRequest) {
        return ResponseEntity.status(201).body(orderMapper.map(orderService.save(orderMapper.map(orderRequest),orderMapper.mapPayment(orderRequest.getPaymentType(),orderRequest.getPaymentParams()))));
    }

    @Override
    public ResponseEntity<StatusResponse> updateStatus(Long id, OrderStatusRequest orderStatusRequest) {
        return ResponseEntity.ok(orderMapper.map(orderService.updateStatus(orderMapper.map(id, orderStatusRequest))));
    }
}



