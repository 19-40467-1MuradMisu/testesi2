package com.deliveryservice.deliveryservice.controller;

import com.deliveryservice.deliveryservice.dto.DeliveryRequestDto;
import com.deliveryservice.deliveryservice.model.DeliveryStatus;
import com.deliveryservice.deliveryservice.service.DeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/delivery")
    public ResponseEntity<String> createDelivery(@RequestBody DeliveryRequestDto dto) {
        deliveryService.createDelivery(dto);
        return ResponseEntity.ok("Delivery created");
    }

    @PutMapping("/delivery/{orderId}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long orderId,
                                               @RequestParam DeliveryStatus status) {
        deliveryService.updateDeliveryStatus(orderId, status);
        return ResponseEntity.ok("Delivery status updated");
    }
}
