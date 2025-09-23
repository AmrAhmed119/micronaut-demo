package com.example.repository;

import com.example.entity.Customer;
import com.example.exception.CustomerNotFoundException;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class CustomerRepositoryFacade {

    private final CustomerRepository customerRepository;

    public CustomerRepositoryFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
