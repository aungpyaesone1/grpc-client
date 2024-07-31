package com.aps.grpc_client.controller;

import com.aps.grpc_client.dto.BaseResponse;
import com.aps.grpc_client.dto.CreateEmployeeRequest;
import com.aps.grpc_client.dto.Employee;
import com.aps.grpc_client.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        System.out.println("here");
        Employee employee = companyService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("employee")
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest request) {
        BaseResponse response = companyService.createEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("employee-list")
    public ResponseEntity<?> getEmployeeList(@RequestParam int page, @RequestParam int limit) {
        return new ResponseEntity<>(companyService.getEmployeeList(page, limit), HttpStatus.OK);
    }
}
