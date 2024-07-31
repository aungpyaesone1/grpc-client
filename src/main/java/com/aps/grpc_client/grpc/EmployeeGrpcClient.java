package com.aps.grpc_client.grpc;

import com.aps.employee.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class EmployeeGrpcClient {
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub;
    private ManagedChannel managedChannel;

    @PostConstruct
    private void init() {
        managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .keepAliveWithoutCalls(true)
                .usePlaintext().build();
        employeeServiceBlockingStub = EmployeeServiceGrpc.newBlockingStub(managedChannel);
    }

    @PreDestroy
    private void destroy() {
        managedChannel.shutdownNow();
        managedChannel = null;
        employeeServiceBlockingStub = null;
    }

    public EmployeeResponse getEmployee(EmployeeRequest request) {
        return employeeServiceBlockingStub.getEmployee(request);
    }

    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) {
        return employeeServiceBlockingStub.createEmployee(request);
    }

    public EmployeeListResponse getEmployeeList(EmployeeListRequest request) {
        return employeeServiceBlockingStub.getEmployeeList(request);
    }
}
