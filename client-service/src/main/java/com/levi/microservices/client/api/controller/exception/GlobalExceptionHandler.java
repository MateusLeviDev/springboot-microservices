package com.levi.microservices.client.api.controller.exception;

import com.levi.microservices.client.domain.model.exception.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Pattern;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String ENTITY_NOT_FOUND = "Entity not found";
    private static final String PACKAGE_NAME_PREFIX = "(com.levi.microservices.client+\\.)";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO<Object>> notFoundException(final EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionDTO.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(ENTITY_NOT_FOUND)
                        .details(removePackageName(e.getMessage()))
                        .build());
    }

    private String removePackageName(final String input) {
        final var pattern = Pattern.compile(PACKAGE_NAME_PREFIX, Pattern.MULTILINE);
        final var matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }

}
