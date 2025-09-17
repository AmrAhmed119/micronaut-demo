package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;

import java.util.List;

/**
 * Service interface for managing Customer entities.
 */
public interface CustomerService {
    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers.
     */
    List<Customer> findAll();

    /**
     * Retrieves a single customer by ID.
     *
     * @param id The ID of the customer.
     * @return The found Customer.
     * @throws com.example.exception.CustomerNotFoundException if no customer is found with the given ID.
     */
    Customer findById(Long id);

    /**
     * Retrieves a single customer by email.
     *
     * @param email The email of the customer.
     * @return The found Customer.
     * @throws com.example.exception.CustomerNotFoundException if no customer is found with the given email.
     */
    Customer findByEmail(String email);

    /**
     * Creates a new customer.
     *
     * @param customer customer with a name and email.
     * @return The created customer with an assigned ID.
     * @throws com.example.exception.DuplicateEmailException if a customer with the same email already exists.
     */
    Customer create(CustomerDTO customer);

    /**
     * Updates an existing customer.
     *
     * @param id The ID of the customer to update.
     * @param updated The updated customer data.
     * @return The updated customer.
     * @throws com.example.exception.CustomerNotFoundException if the customer does not exist.
     * @throws com.example.exception.DuplicateEmailException if updating the email to one that already exists.
     */
    Customer update(Long id, CustomerDTO updated);

    /**
     * Deletes a customer by ID.
     *
     * @param id The ID of the customer to delete.
     * @throws com.example.exception.CustomerNotFoundException if the customer does not exist.
     */
    void delete(Long id);
}
