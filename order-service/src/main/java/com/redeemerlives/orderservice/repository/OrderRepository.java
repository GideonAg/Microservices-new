package com.redeemerlives.orderservice.repository;

import com.redeemerlives.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
