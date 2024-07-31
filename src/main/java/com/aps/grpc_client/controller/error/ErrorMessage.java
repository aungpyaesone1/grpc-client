package com.aps.grpc_client.controller.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String statusCode;
    private String message;
    private Date timestamp;
}
