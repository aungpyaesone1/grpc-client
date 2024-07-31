package com.aps.grpc_client.service;

import com.aps.grpc_client.dto.BaseResponse;
import com.aps.grpc_client.dto.CreateEmployeeRequest;
import com.aps.grpc_client.dto.Employee;
import com.aps.grpc_client.dto.EmployeeListResponse;

public interface CompanyService {
    public Employee getEmployee(String id);
    public BaseResponse createEmployee(CreateEmployeeRequest request);

    public EmployeeListResponse getEmployeeList(int page, int limit);
}
