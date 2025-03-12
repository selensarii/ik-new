package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
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
    private final ProjectService projectService;

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public CreateEmployeeResponseDTO addEmployee(@RequestBody CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        return employeeService.addEmployee(createEmployeeRequestDTO);
    }


    @GetMapping("/v1/{employeeId}")
    public EmployeeResponseDto getById(@PathVariable String employeeId) {
        return employeeService.getById(employeeId);
    }

    @PutMapping("/v1/{employeeId}")
    public UpdateEmployeeResponseDTO updateEmployee(@PathVariable("employeeId") String employeeId,
                                                    @RequestBody UpdateEmployeeRequestDTO updateEmployeeRequestDTO) {

        updateEmployeeRequestDTO.setId(UUID.fromString(employeeId));
        return employeeService.updateEmployee(updateEmployeeRequestDTO);
    }

    @DeleteMapping("/v1/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Çalışan başarıyla silindi: " + employeeId);
    }

    @GetMapping("/v1/fullName/{employeeId}")
    public String getEmployeeFullName(@PathVariable String employeeId) {
        return employeeService.getFindEmployeeFullNameById(employeeId);
    }
}