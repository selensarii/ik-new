package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.EmployeeRequest;
import com.example.ysoft.business.dtos.responses.EmployeeResponse;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.library.EmployeeNotFoundException;
import com.example.ysoft.dataAccess.EmployeeRepository;
import com.example.ysoft.library.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeResponse::toResponse).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        UUID projectId = employeeRequest.getProjectId(); // Eğer getProjectId() zaten UUID döndürüyorsa

        // Project'i UUID ile bulma
        Project project = projectService.findById(projectId.toString()); // Eğer findById() String kabul ediyorsa

        Employee employee = Employee.builder()
                .fullName(employeeRequest.getFullName())
                .identityNumber(employeeRequest.getIdentityNumber())
                .position(employeeRequest.getPosition())
                .salary(employeeRequest.getSalary())
                .project(project)
                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeResponse.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse getById(String id) {
        UUID employeeId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee bulunamadı"));
        return EmployeeResponse.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(String id, EmployeeRequest employeeRequest) {
        UUID employeeId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        return employeeRepository.findById(employeeId)
                .map(existingEmployee -> {
                    Project project = projectService.findById(String.valueOf(employeeRequest.getProjectId())); // Projeyi bulma
                    existingEmployee.setFullName(employeeRequest.getFullName());
                    existingEmployee.setPosition(employeeRequest.getPosition());
                    existingEmployee.setIdentityNumber(employeeRequest.getIdentityNumber());
                    existingEmployee.setSalary(employeeRequest.getSalary());
                    existingEmployee.setProject(project);
                    return employeeRepository.save(existingEmployee);
                })
                .map(EmployeeResponse::toResponse)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee güncellenemedi"));
    }

    @Override
    public void deleteEmployee(String id) {
        UUID employeeId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        employeeRepository.deleteById(employeeId);
    }
}
