package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.library.EmployeeNotFoundException;
import com.example.ysoft.dataAccess.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeResponseDto::toResponse).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {
        UUID projectId = employeeRequestDto.getProjectId(); // Eğer getProjectId() zaten UUID döndürüyorsa

        // Project'i UUID ile bulma
        Project project = projectService.findById(projectId.toString()); // Eğer findById() String kabul ediyorsa

        Employee employee = Employee.builder()
                .fullName(employeeRequestDto.getFullName())
                .identityNumber(employeeRequestDto.getIdentityNumber())
                .position(employeeRequestDto.getPosition())
                .salary(employeeRequestDto.getSalary())
                .project(project)
                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeResponseDto.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponseDto getById(String id) {
        UUID employeeId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee bulunamadı"));
        return EmployeeResponseDto.toResponse(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(String id, EmployeeRequestDto employeeRequestDto) {
        UUID employeeId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        return employeeRepository.findById(employeeId)
                .map(existingEmployee -> {
                    Project project = projectService.findById(String.valueOf(employeeRequestDto.getProjectId())); // Projeyi bulma
                    existingEmployee.setFullName(employeeRequestDto.getFullName());
                    existingEmployee.setPosition(employeeRequestDto.getPosition());
                    existingEmployee.setIdentityNumber(employeeRequestDto.getIdentityNumber());
                    existingEmployee.setSalary(employeeRequestDto.getSalary());
                    existingEmployee.setProject(project);
                    return employeeRepository.save(existingEmployee);
                })
                .map(EmployeeResponseDto::toResponse)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee güncellenemedi"));
    }

    @Override
    public void deleteEmployee(String id) {
        UUID employeeId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        employeeRepository.deleteById(employeeId);
    }
}
