package com.deliveryservice.deliveryservice;

import com.deliveryservice.deliveryservice.model.Delivery;
import com.deliveryservice.deliveryservice.model.DeliveryStatus;
import com.deliveryservice.deliveryservice.repository.DeliveryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryInitializer {

    @Bean
    public CommandLineRunner loadDeliveryData(DeliveryRepository deliveryRepository) {
        return args -> {
            Delivery delivery1 = Delivery.builder()
                    .orderId(1001L)
                    .deliveryAddress("Dhaka, Bangladesh")
                    .deliveryStatus(DeliveryStatus.DELIVERY_PENDING)
                    .build();

            Delivery delivery2 = Delivery.builder()
                    .orderId(1002L)
                    .deliveryAddress("Chittagong, Bangladesh")
                    .deliveryStatus(DeliveryStatus.OUT_FOR_DELIVERY)
                    .build();

            deliveryRepository.save(delivery1);
            deliveryRepository.save(delivery2);
        };
    }
}
