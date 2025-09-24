package com.example.repository;

import com.example.entity.Customer;
import com.example.jms.JmsPublisher;
import com.example.kafka.KafkaPublisher;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class CustomerRepositoryFacade {

    private final CustomerRepository customerRepository;
    private final KafkaPublisher kafkaPublisher;
    private final JmsPublisher jmsPublisher;

    public CustomerRepositoryFacade(
            CustomerRepository customerRepository,
            KafkaPublisher kafkaPublisher,
            JmsPublisher jmsPublisher
    ) {
        this.customerRepository = customerRepository;
        this.kafkaPublisher = kafkaPublisher;
        this.jmsPublisher = jmsPublisher;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer save(Customer customer) {
        final Customer savedCustomer = customerRepository.save(customer);

        // Notify other systems about the new customer
        kafkaPublisher.notifyCustomerCreation(savedCustomer);
        jmsPublisher.notifyCustomerCreation(savedCustomer);

        return savedCustomer;
    }

    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
