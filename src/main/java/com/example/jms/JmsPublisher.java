package com.example.jms;

import com.example.entity.Customer;
import io.micronaut.jms.annotations.JMSProducer;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;

import static io.micronaut.jms.activemq.classic.configuration.ActiveMqClassicConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSProducer(CONNECTION_FACTORY_BEAN_NAME)
public interface JmsPublisher {
    @Queue("customer-creation")
    void notifyCustomerCreation(@MessageBody Customer customer);
}
