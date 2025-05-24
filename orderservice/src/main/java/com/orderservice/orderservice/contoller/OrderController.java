package com.orderservice.orderservice.contoller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.orderservice.dto.OrderDto;
import com.orderservice.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
    return orderService.getAllOrders();
    }
    
    @PostMapping("/orders")
    public ResponseEntity<String>  createOrder(@RequestBody OrderDto orderDto) {
    orderService.addOrder(orderDto);
    return ResponseEntity.ok("Order created");

    }
}
