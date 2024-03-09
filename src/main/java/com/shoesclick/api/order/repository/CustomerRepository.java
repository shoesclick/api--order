package com.shoesclick.api.order.repository;


import com.shoesclick.api.order.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "customerClient", url = "${backend.services.customerUrl}")
public interface CustomerRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/api/customer/{id}", produces = "application/json")
    Customer findById(@PathVariable("id") Long id);

}
