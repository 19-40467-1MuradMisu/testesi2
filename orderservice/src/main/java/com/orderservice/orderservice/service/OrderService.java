package com.orderservice.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.orderservice.orderservice.dto.OrderDto;
import com.orderservice.orderservice.model.Order;
import com.orderservice.orderservice.model.OrderStatus;
import com.orderservice.orderservice.model.PaymentStatus;
import com.orderservice.orderservice.model.DeliveryStatus;


import com.orderservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders.stream().map(this::mapToOrderDto).toList();
    }

    private OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .price(order.getPrice())
                .productId(order.getProductId())
                .build();
    }

    public void addOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .price(orderDto.getPrice())
                .productId(orderDto.getProductId())
                .build();

        // Setting the Order status to CREATED, the payment status to Pending, and delivery status to Pending
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        order.setPaymentStatus(PaymentStatus.PAYMENT_PENDING);
        order.setDeliveryStatus(DeliveryStatus.DELIVERY_PENDING);

        // Save the order to in its current state in the Database
        orderRepository.save(order);
    }

}
