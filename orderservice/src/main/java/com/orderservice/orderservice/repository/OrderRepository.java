package com.orderservice.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}