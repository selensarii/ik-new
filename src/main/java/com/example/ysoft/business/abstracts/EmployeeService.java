package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getById(String id);
    EmployeeResponseDto updateEmployee(String id, EmployeeRequestDto employeeRequestDto);
    void deleteEmployee(String id);
}
