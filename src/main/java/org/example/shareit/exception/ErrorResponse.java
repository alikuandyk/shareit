package org.example.shareit.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private final String error;
    private final HttpStatus httpStatus;
}
