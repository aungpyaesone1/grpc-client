package com.aps.grpc_client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String username;
    private String address;
    private String phone;
    private String mail;
}
