package com.aps.grpc_client.service.impl;

import com.aps.employee.model.CreateEmployeeResponse;
import com.aps.employee.model.EmployeeListRequest;
import com.aps.employee.model.EmployeeRequest;
import com.aps.employee.model.EmployeeResponse;
import com.aps.grpc_client.controller.error.CustomException;
import com.aps.grpc_client.dto.BaseResponse;
import com.aps.grpc_client.dto.CreateEmployeeRequest;
import com.aps.grpc_client.dto.Employee;
import com.aps.grpc_client.dto.EmployeeListResponse;
import com.aps.grpc_client.grpc.EmployeeGrpcClient;
import com.aps.grpc_client.service.CompanyService;
import io.grpc.StatusRuntimeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private EmployeeGrpcClient employeeGrpcClient;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Employee getEmployee(String id) {
        try {
            EmployeeRequest  request = EmployeeRequest.newBuilder()
                    .setId(String.valueOf(id)).build();
            EmployeeResponse grpcResponse = employeeGrpcClient.getEmployee(request);
            Employee employee = modelMapper.map(grpcResponse, Employee.class);
            return employee;
        }
        catch (StatusRuntimeException sre) {
            throw new CustomException(HttpStatus.BAD_REQUEST, sre.getMessage(), new Date());
        }
        catch (Exception e) {
            throw new RuntimeException("Internal Server Error");
        }

    }

    @Override
    public BaseResponse createEmployee(CreateEmployeeRequest request) {
        try {
            com.aps.employee.model.CreateEmployeeRequest grpcRequest = com.aps.employee.model.CreateEmployeeRequest.newBuilder()
                    .setUsername(request.getUsername())
                    .setAddress(request.getAddress())
                    .setPhone(request.getPhone())
                    .setMail(request.getMail()).build();
            CreateEmployeeResponse grpcResponse = employeeGrpcClient.createEmployee(grpcRequest);
            return modelMapper.map(grpcResponse, BaseResponse.class);
        }
        catch (StatusRuntimeException sre) {
            throw new CustomException(HttpStatus.BAD_REQUEST, sre.getMessage(), new Date());
        }
        catch (Exception e) {
            throw new RuntimeException("Internal Server Error");
        }
    }

    @Override
    public EmployeeListResponse getEmployeeList(int page, int limit) {
        EmployeeListRequest request = EmployeeListRequest.newBuilder()
                .setPage(page)
                .setLimit(limit)
                        .build();
        com.aps.employee.model.EmployeeListResponse grpcResponse = employeeGrpcClient.getEmployeeList(request);
        return modelMapper.map(grpcResponse, EmployeeListResponse.class);
    }
}
