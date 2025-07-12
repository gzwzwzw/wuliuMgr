package com.logistics.exception;

import lombok.Data;
import java.time.Instant;

@Data
public class ErrorResponse {
    private final int statusCode;
    private final String error;
    private final String message;
    private final Instant timestamp;
    
    public ErrorResponse(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = Instant.now();
    }
}