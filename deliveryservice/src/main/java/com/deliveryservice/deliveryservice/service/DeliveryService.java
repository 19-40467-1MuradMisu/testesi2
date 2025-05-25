package com.deliveryservice.deliveryservice.service;

import com.deliveryservice.deliveryservice.dto.DeliveryRequestDto;
import com.deliveryservice.deliveryservice.model.Delivery;
import com.deliveryservice.deliveryservice.model.DeliveryStatus;
import com.deliveryservice.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void createDelivery(DeliveryRequestDto dto) {
        Delivery delivery = Delivery.builder()
                .orderId(dto.getOrderId())
                .deliveryAddress(dto.getDeliveryAddress())
                .deliveryStatus(DeliveryStatus.DELIVERY_PENDING)
                .build();

        deliveryRepository.save(delivery);
    }

    public void updateDeliveryStatus(Long orderId, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setDeliveryStatus(status);
        deliveryRepository.save(delivery);
    }
}
