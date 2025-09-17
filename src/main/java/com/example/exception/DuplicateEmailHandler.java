package com.example.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

/**
 * Exception handler for DuplicateEmailException.
 * Returns HTTP 400 Bad Request when the email already exists.
 */
@Produces
@Singleton
public class DuplicateEmailHandler implements ExceptionHandler<DuplicateEmailException, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, DuplicateEmailException exception) {
        return HttpResponse.badRequest(exception.getMessage());
    }
}
