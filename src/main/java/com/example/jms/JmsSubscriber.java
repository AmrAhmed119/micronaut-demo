package com.example.jms;

import com.example.entity.Customer;
import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.micronaut.jms.activemq.classic.configuration.ActiveMqClassicConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
public class JmsSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(JmsSubscriber.class);

    @Queue("customer-creation")
    public void receiveNotification(@MessageBody Customer customer) {
        logger.info("[JMS] New customer created: id={}, name={}, email={}",
                customer.getId(), customer.getName(), customer.getEmail());
    }
}
