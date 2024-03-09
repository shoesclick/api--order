package com.shoesclick.api.order.repository;


import com.shoesclick.api.order.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "productClient", url = "${backend.services.productUrl}")
public interface ProductRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/{id}", produces = "application/json")
    Product findById(@PathVariable("id") Long id);

}
