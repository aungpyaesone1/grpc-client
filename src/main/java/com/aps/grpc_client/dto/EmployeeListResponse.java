package com.aps.grpc_client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListResponse {
    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<Employee> data;
}
