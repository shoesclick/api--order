package com.shoesclick.api.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Status;
import com.shoesclick.api.order.exception.ElementNotFoundException;
import com.shoesclick.api.order.exception.ListNotFoundException;
import com.shoesclick.api.order.handler.ControllerErrorHandler;
import com.shoesclick.api.order.mapper.OrderMapper;
import com.shoesclick.api.order.openapi.model.domain.OrderRequest;
import com.shoesclick.api.order.openapi.model.domain.OrderStatusRequest;
import com.shoesclick.api.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderApiImplTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Spy
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderApiImpl controller;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerErrorHandler())
                .build();
    }

    @Test
    void shouldReturnHttp201_SaveOrder() throws Exception {
        when(orderService.save(any(Order.class), any(PaymentDomain.class))).thenReturn(new Status(0, ""));
        when(orderMapper.map(any(OrderRequest.class))).thenReturn(new Order());
        when(orderMapper.mapPayment(anyString(), anyMap())).thenReturn(getPaymentDomain());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/order/save")
                        .content(new ObjectMapper().writeValueAsString(getOrderRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    @Test
    void shouldReturnHttp200_GetOrder() throws Exception {
        when(orderService.findById(anyLong())).thenReturn(new Order());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    void shouldReturnHttp404_GetOrder() throws Exception {
        when(orderService.findById(anyLong())).thenThrow(new ElementNotFoundException("ERRO"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }


    @Test
    void shouldReturnHttp200_ListOrderByCustomer() throws Exception {
        when(orderService.listOrderByCustomer(anyLong())).thenReturn(List.of(new Order()));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/list/{idCustomer}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }


    @Test
    void shouldReturnHttp204_ListOrderByCustomer() throws Exception {
        when(orderService.listOrderByCustomer(anyLong())).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/list/{idCustomer}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    void shouldReturnHttp200_UpdateStatus() throws Exception {
        when(orderMapper.map(anyLong(), any(OrderStatusRequest.class))).thenReturn(new Order());
        when(orderService.updateStatus(any(Order.class))).thenReturn(new Status(0, ""));
         mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/order/{id}/status",1L)
                        .content(new ObjectMapper().writeValueAsString(getOrderStatusRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    private OrderStatusRequest getOrderStatusRequest() {
        var orderStatus = new OrderStatusRequest();
        orderStatus.setStatus(1);
        return orderStatus;
    }

    private OrderRequest getOrderRequest() {
        var request = new OrderRequest();
        request.setPaymentType("PIX_PAYMENT");
        request.setPaymentParams(Map.of("keyCode", "3423423234234234324234234"));
        return request;
    }

    private PaymentDomain getPaymentDomain() {
        return new PaymentDomain()
                .setPaymentType("PIX_PAYMENT")
                .setPaymentParams(Map.of("keyCode", "3423423234234234324234234"));

    }


}
