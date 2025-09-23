package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.exception.CustomerNotFoundException;
import com.example.exception.DuplicateEmailException;
import com.example.repository.CustomerRepositoryFacade;
import jakarta.inject.Singleton;
import jakarta.persistence.PersistenceException;

import java.util.List;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepositoryFacade customerRepositoryFacade;

    public CustomerServiceImpl(CustomerRepositoryFacade customerRepositoryFacade) {
        this.customerRepositoryFacade = customerRepositoryFacade;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepositoryFacade.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepositoryFacade.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepositoryFacade.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException(email));
    }

    @Override
    public Customer create(CustomerDTO customer) {
        Customer newCustomer = new Customer(null, customer.getName(), customer.getEmail());
        try {
            return customerRepositoryFacade.save(newCustomer);
        } catch (PersistenceException e) {
            throw new DuplicateEmailException(customer.getEmail());
        }
    }

    @Override
    public Customer update(Long id, CustomerDTO updated) {
        Customer existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        try {
            return customerRepositoryFacade.update(existing);
        } catch (PersistenceException e) {
            throw new DuplicateEmailException(existing.getEmail());
        }
    }

    @Override
    public void delete(Long id) {
        Customer customer = findById(id);
        customerRepositoryFacade.delete(customer);
    }
}
