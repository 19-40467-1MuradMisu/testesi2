package com.deliveryservice.deliveryservice.service;

import com.deliveryservice.deliveryservice.dto.OrderDto;
import com.deliveryservice.deliveryservice.model.Delivery;
import com.deliveryservice.deliveryservice.model.DeliveryStatus;
import com.deliveryservice.deliveryservice.model.PaymentStatus;
import com.deliveryservice.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
@Autowired
private final DeliveryRepository deliveryRepository;
private final KafkaTemplate<String, OrderDto> kafkaTemplate;


@KafkaListener(topics = "paymentTopic", groupId = "delivery-group")
public void completeDelivery(OrderDto orderDto) {
    //  Verify payment is truly completed (from DB or DTO)
        if (orderDto.getPaymentStatus() != PaymentStatus.PAYMENT_COMPLETED) {
        
                return;
        }

    // Set delivery status
        orderDto.setDeliveryStatus(DeliveryStatus.DELIVERED);

    // Create and save delivery
        Delivery delivery = Delivery.builder()
                .orderId(orderDto.getId())
                .customerName(orderDto.getUserId())
                .deliveryStatus(orderDto.getDeliveryStatus())
                .build();

        deliveryRepository.save(delivery);

    // Send back to OrderService
        kafkaTemplate.send("deliveryTopic", orderDto);

}



public Delivery getDeliveryByOrderId(Integer orderId) {
return deliveryRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("Delivery not found for order ID: " + orderId));
}
}