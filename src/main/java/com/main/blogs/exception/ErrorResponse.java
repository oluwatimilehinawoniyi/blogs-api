package com.main.blogs.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(List<String> message,
                            String error,
                            HttpStatus status,
                            int statusCode,
                            Instant timestamp
) {
    public ErrorResponse(List<String> message,
                         Throwable throwable,
                         HttpStatus status,
                         int statusCode
    ) {
        this(message,
                throwable.getClass().getSimpleName(),
                status,
                statusCode,
                Instant.now());
    }
}
