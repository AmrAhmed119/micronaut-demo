package com.example.kafka;

import com.example.entity.Customer;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener
public class KafkaSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(KafkaSubscriber.class);

    @Topic("customer-creation")
    public void receiveNotification(Customer customer) {
        logger.info("[KAFKA] New customer created: id={}, name={}, email={}",
                customer.getId(), customer.getName(), customer.getEmail());
    }
}
