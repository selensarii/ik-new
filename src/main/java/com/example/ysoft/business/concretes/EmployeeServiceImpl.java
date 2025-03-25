package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.core.mapper.MapperService;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.library.EmployeeNotFoundException;
import com.example.ysoft.dataAccess.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.ysoft.core.utils.MessageConstant.EMPLOYEE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;
    private final MapperService mapperService;

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(mapperService::toResponsee)
                .collect(Collectors.toList());
    }

    @Override
    public CreateEmployeeResponseDTO addEmployee(CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        Project project = projectService.findById(createEmployeeRequestDTO.getProjectId().toString());
        Employee employee = mapperService.toEntity(createEmployeeRequestDTO, project);
        Employee savedEmployee = employeeRepository.save(employee);

        return mapperService.toCreateEmployeeResponse(savedEmployee);
    }
    
    @Override
    public EmployeeResponseDto getById(String id) {
        UUID employeeId = UUID.fromString(id);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        return mapperService.toResponsee(employee);
    }

    @Override
    public UpdateEmployeeResponseDTO updateEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDTO) {
        UUID employeeId = updateEmployeeRequestDTO.getId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        return mapperService.toUpdateEmployeeResponse(employee);
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
}
