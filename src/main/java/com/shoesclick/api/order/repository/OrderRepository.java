package com.shoesclick.api.order.repository;

import com.shoesclick.api.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByIdCustomer(Long idCustomer);

}
