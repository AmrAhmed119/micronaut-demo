package com.example.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

/**
 * Exception handler for CustomerNotFoundException.
 * Returns HTTP 404 with an error message when a customer is not found.
 */
@Produces
@Singleton
public class CustomerNotFoundHandler implements ExceptionHandler<CustomerNotFoundException, HttpResponse<?>> {
    @Override
    public HttpResponse<?> handle(HttpRequest request, CustomerNotFoundException exception) {
        return HttpResponse.notFound(exception.getMessage());
    }
}