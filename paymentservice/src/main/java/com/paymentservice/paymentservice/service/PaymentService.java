package com.paymentservice.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
private final KafkaTemplate<String, OrderDto> kafkaTemplate;

 @KafkaListener(topics = "orderCreatedTopic", groupId = "payment-group")
public void createPayment(OrderDto orderDto) {
    log.info("Creating payment for order: {}", orderDto);

    if (paymentRepository.existsById(orderDto.getId())) {
        log.warn("Payment already exists for Order ID: {}", orderDto.getId());
        return; // Skip duplicate
    }

    // update payment status
    orderDto.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);

    Payment payment = Payment.builder()
            .orderId(orderDto.getId())
            .userId(orderDto.getUserId())
            .paymentStatus(orderDto.getPaymentStatus())
            .build();

    Payment savedPayment = paymentRepository.save(payment);

    kafkaTemplate.send("paymentTopic", orderDto);
    log.info("Payment created successfully with ID: {}", savedPayment.getOrderId());
}
/*@KafkaListener(topics = "orderCreatedTopic", groupId = "payment-group")
public void listenOrderCreated(OrderDto orderDto) {
    log.info("Received order: {}", orderDto);

    // Save with payment status = PAYMENT_PENDING
    Payment payment = Payment.builder()
            .orderId(orderDto.getId())
            .userId(orderDto.getUserId())
            .paymentStatus(PaymentStatus.PAYMENT_PENDING)
            .build();

    paymentRepository.save(payment);
}*/

}
