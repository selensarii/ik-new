package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @PostMapping
    public CreateEmployeeResponseDTO addEmployee(@RequestBody CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        return employeeService.addEmployee(createEmployeeRequestDTO);
    }

    @PutMapping("/v1/employeeId")
    public UpdateEmployeeResponseDTO updateEmployee(@RequestBody UpdateEmployeeRequestDTO updateEmployeeRequestDTO) throws JsonProcessingException {
        return employeeService.updateEmployee(updateEmployeeRequestDTO);
    }

    @DeleteMapping("/v1/employeeId/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("calisan basariyla silindi: " + employeeId);
    }

    @GetMapping("/v1/fullName/employeeId/{employeeId}")
    public String getEmployeeFullName(@PathVariable String employeeId) {
        return employeeService.getFindEmployeeFullNameById(employeeId);
    }
}