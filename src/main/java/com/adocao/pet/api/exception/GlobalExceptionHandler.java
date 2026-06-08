package com.adocao.pet.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRule(
            BusinessRuleException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Business Rule Violation",
                ex.getMessage()
        );

        return ResponseEntity.badRequest()
                .body(error);
    }
}