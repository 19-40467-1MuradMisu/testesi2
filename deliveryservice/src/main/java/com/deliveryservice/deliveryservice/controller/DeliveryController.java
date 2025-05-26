package com.deliveryservice.deliveryservice.controller;

import com.deliveryservice.deliveryservice.dto.OrderDto;
import com.deliveryservice.deliveryservice.model.Delivery;
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
    public ResponseEntity<String> completeDelivery(@RequestBody OrderDto orderDto) {
        deliveryService.completeDelivery(orderDto);
        return ResponseEntity.ok("Delivery marked as completed");
}



@GetMapping("/delivery/{orderId}")
    public ResponseEntity<Delivery> getDeliveryByOrderId(@PathVariable Integer orderId) {
        Delivery delivery = deliveryService.getDeliveryByOrderId(orderId);
        return ResponseEntity.ok(delivery);
    }
    
}
