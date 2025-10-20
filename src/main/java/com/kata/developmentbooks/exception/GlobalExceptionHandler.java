package com.kata.developmentbooks.exception;

import com.kata.developmentbooks.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse("Invalid input", ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralError(Exception ex) {
        return ResponseEntity.internalServerError().body(
                new ErrorResponse("Internal server error", ex.getMessage())
        );
    }
}