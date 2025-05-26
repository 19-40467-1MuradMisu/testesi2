package com.orderservice.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


/* Task 1   */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic OrderTopicCreation(){
    return TopicBuilder.name("orderCreatedTopic")
    .build();
    }

    @Bean
    public NewTopic PaymentTopicCreation(){
    return TopicBuilder.name("paymentTopic")
    .build();
    }

    @Bean
    public NewTopic DeliveryTopic(){
    return TopicBuilder.name("deliveryTopic")
    .build();
    }
}
