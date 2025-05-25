package com.paymentservice.paymentservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paymentservice.paymentservice.model.Payment;
import com.paymentservice.paymentservice.repository.PaymentRepository;


import java.math.BigDecimal;


@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadProductsData(PaymentRepository paymentRepository) {
        return args -> {
            Payment userBalance1 = new Payment();
			userBalance1.setUserId(01);
			userBalance1.setBalance(BigDecimal.valueOf(1233));

			paymentRepository.save(userBalance1);

            Payment userBalance2 = new Payment();
			userBalance2.setUserId(02);
			userBalance2.setBalance(BigDecimal.valueOf(1399));

			paymentRepository.save(userBalance2);
        };
    }
}
