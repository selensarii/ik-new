package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
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
    public CreateEmployeeResponseDTO addEmployee(CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        Project project = projectService.findById(createEmployeeRequestDTO.getProjectId().toString());
        Employee employee = toEntity(createEmployeeRequestDTO, project);
        Employee savedEmployee = employeeRepository.save(employee);

        return toCreateEmployeeResponse(savedEmployee);
    }
    @Override
    public EmployeeResponseDto getById(String id) {
        UUID employeeId = UUID.fromString(id);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee bulunamadı"));
        return EmployeeResponseDto.toResponse(employee);
    }

    @Override
    public UpdateEmployeeResponseDTO updateEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDTO) {
        UUID employeeId = updateEmployeeRequestDTO.getId();

        return employeeRepository.findById(employeeId)
                .map(existingEmployee -> {
                    UUID projectId = updateEmployeeRequestDTO.getProjectId();
                    Project project = projectService.findById(String.valueOf(projectId));

                    // Çalışan bilgilerini güncelliyoruz
                    existingEmployee.setFullName(updateEmployeeRequestDTO.getFullName());
                    existingEmployee.setPosition(updateEmployeeRequestDTO.getPosition());
                    existingEmployee.setIdentityNumber(updateEmployeeRequestDTO.getIdentityNumber());
                    existingEmployee.setSalary(updateEmployeeRequestDTO.getSalary());
                    existingEmployee.setProject(project);

                    return employeeRepository.save(existingEmployee);
                })
                .map(UpdateEmployeeResponseDTO::fromEmployee)
                .orElseThrow(() -> new EmployeeNotFoundException("Çalışan bulunamadı veya güncelleme başarısız"));
    }



    @Override
    public void deleteEmployee(String id) {
        UUID employeeId = UUID.fromString(id);
        employeeRepository.deleteById(employeeId);
    }
    @Override
    public String getFindEmployeeFullNameById(String id) {
        return employeeRepository.findEmployeeFullNameById(UUID.fromString(id));
    }

    //DÖNÜŞÜMLER
    private CreateEmployeeResponseDTO toCreateEmployeeResponse(Employee savedEmployee) {
        return CreateEmployeeResponseDTO.builder()
                .id(savedEmployee.getId())
                .fullName(savedEmployee.getFullName())
                .position(savedEmployee.getPosition())
                .identityNumber(savedEmployee.getIdentityNumber())
                .salary(savedEmployee.getSalary())
                .projectId(savedEmployee.getProject() != null ? savedEmployee.getProject().getId() : null)
                .build();
    }
    private Employee toEntity(CreateEmployeeRequestDTO createEmployeeRequestDTO, Project project) {
        return Employee.builder()
                .fullName(createEmployeeRequestDTO.getFullName())
                .position(createEmployeeRequestDTO.getPosition())
                .identityNumber(createEmployeeRequestDTO.getIdentityNumber())
                .salary(createEmployeeRequestDTO.getSalary())
                .project(project)
                .build();
    }
}
