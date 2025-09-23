package com.example.controller;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Get
    public List<Customer> finaAll() {
        return customerService.findAll();
    }

    @Get("/{id}")
    public Customer findById(@PathVariable long id) {
        return customerService.findById(id);
    }

    @Post
    public HttpResponse<Customer> create(@Body @Valid CustomerDTO customer) {
        return HttpResponse.created(customerService.create(customer));
    }

    @Put("/{id}")
    public Customer update(@PathVariable Long id, @Body @Valid CustomerDTO customer) {
        return customerService.update(id, customer);
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return HttpResponse.noContent();
    }
}
