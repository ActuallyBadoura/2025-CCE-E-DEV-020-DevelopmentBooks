package com.kata.developmentbooks.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error details returned when a request is invalid")
public class ErrorResponse {
    @Schema(description = "A short, human-readable error title", example = "Invalid basket data")
    private String error;

    @Schema(description = "Detailed message describing the problem", example = "Book IDs must be between 1 and 5")
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
