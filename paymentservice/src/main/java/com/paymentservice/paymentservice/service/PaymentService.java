package com.paymentservice.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentservice.paymentservice.dto.OrderDto;
import com.paymentservice.paymentservice.dto.OrderStatus;
import com.paymentservice.paymentservice.dto.PaymentStatus;
import com.paymentservice.paymentservice.model.Payment;
import com.paymentservice.paymentservice.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {

@Autowired
private PaymentRepository paymentRepository;


public void processpayment(OrderDto orderDto){

Payment Payment = paymentRepository.findById(orderDto.getUserId()).get();
    log.info("Log message - Payment: {} ", Payment.getBalance());

    if (Payment.getBalance().compareTo(orderDto.getPrice()) == 1) {
        Payment.setBalance(Payment.getBalance().subtract(orderDto.getPrice()));
        paymentRepository.save(Payment);

        orderDto.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
        orderDto.setOrderStatus(OrderStatus.ORDER_COMPLETED);

      } else {

        orderDto.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
        orderDto.setOrderStatus(OrderStatus.ORDER_CANCELLED);

    }
}
}