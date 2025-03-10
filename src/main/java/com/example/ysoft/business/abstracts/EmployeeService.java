package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getById(String id);
    void deleteEmployee(String id);
    String getFindEmployeeFullNameById(String id);

    CreateEmployeeResponseDTO addEmployee(CreateEmployeeRequestDTO createEmployeeRequestDTO);
    UpdateEmployeeResponseDTO updateEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDTO);

}
