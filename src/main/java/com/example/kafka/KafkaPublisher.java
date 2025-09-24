package com.example.kafka;

import com.example.entity.Customer;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface KafkaPublisher {
    @Topic("customer-creation")
    void notifyCustomerCreation(Customer customer);
}
