package com.aps.grpc_client.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalDefaultException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity <?> handleCustomException( CustomException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(ex.getStatusCode().toString());
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setTimestamp(new Date());
        return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage(),
                new Date()
                );
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
