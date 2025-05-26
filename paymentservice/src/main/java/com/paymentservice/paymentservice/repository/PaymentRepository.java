package com.paymentservice.paymentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentservice.paymentservice.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    //Optional<Payment> findByOrderId(Integer orderId);
}
