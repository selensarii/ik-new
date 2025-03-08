package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.EmployeeRequest;
import com.example.ysoft.business.dtos.responses.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse addEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse getById(String id);
    EmployeeResponse updateEmployee(String id, EmployeeRequest employeeRequest);
    void deleteEmployee(String id);
}
