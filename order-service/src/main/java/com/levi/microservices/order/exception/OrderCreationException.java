package com.levi.microservices.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;

public class OrderCreationException extends ErrorResponseException {
    public OrderCreationException(final String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(final String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, message);
        problemDetail.setType(URI.create("https://levi.com/errors/order-creation-failed"));
        problemDetail.setTitle("Order creation failed");
        return problemDetail;
    }
}
