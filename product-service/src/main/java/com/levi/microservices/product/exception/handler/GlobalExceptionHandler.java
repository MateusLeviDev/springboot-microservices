package com.levi.microservices.product.exception.handler;

import com.levi.microservices.product.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Pattern;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    public static final String ENTITY_NOT_FOUND = "Entity not found";
    private static final String PACKAGE_NAME_PREFIX = "(com.levi.microservices.+\\.)";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO<Object>> notFoundException(final EntityNotFoundException e) {
        log.error(ENTITY_NOT_FOUND, e);
        return ResponseEntity.status(404)
                .body(ExceptionDTO.builder()
                        .code(404)
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
