package com.paymentservice.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentservice.paymentservice.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
