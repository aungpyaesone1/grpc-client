package com.aps.grpc_client.controller.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class CustomException extends RuntimeException {
    private HttpStatus statusCode;
    private String message;
    private Date timestamp;

    public CustomException (HttpStatus statusCode, String message, Date timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }
}
