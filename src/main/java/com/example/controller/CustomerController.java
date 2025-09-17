package com.example.controller;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/customers")
public class CustomerController {

    @Get
    public List<Customer> finaAll() {
        return null;
    }

    @Get("/{id}")
    public Customer findById(@PathVariable long id) {
        return null;
    }

    @Post
    public HttpResponse<Customer> create(@Body CustomerDTO customer) {
        return null;
    }

    @Put("/{id}")
    public Customer update(@PathVariable Long id, @Body CustomerDTO customer) {
        return null;
    }

    @Delete("/{id}")
    public HttpResponse<?> delete(@PathVariable Long id) {
        return HttpResponse.noContent();
    }

}
