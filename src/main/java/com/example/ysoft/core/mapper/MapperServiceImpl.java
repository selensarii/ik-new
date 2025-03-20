package com.example.ysoft.core.mapper;

import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.project.CreateProjectResponseDTO;
import com.example.ysoft.business.dtos.responses.project.UpdateProjectResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.entities.User;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {

    // employee
    public CreateEmployeeResponseDTO toCreateEmployeeResponse(Employee savedEmployee) {
        return new CreateEmployeeResponseDTO(
                savedEmployee.getId(),
                savedEmployee.getFullName(),
                savedEmployee.getPosition(),
                savedEmployee.getIdentityNumber(),
                savedEmployee.getSalary(),
                savedEmployee.getProject() != null ? savedEmployee.getProject().getId() : null
        );
    }

    @Override
    public Employee toEntity(CreateEmployeeRequestDTO createEmployeeRequestDTO, Project project) {
        Employee employee = new Employee();
        employee.setFullName(createEmployeeRequestDTO.getFullName());
        employee.setPosition(createEmployeeRequestDTO.getPosition());
        employee.setIdentityNumber(createEmployeeRequestDTO.getIdentityNumber());
        employee.setSalary(createEmployeeRequestDTO.getSalary());
        employee.setProject(project);
        return employee;
    }

    // project
    @Override
    public ProjectResponseDto toResponse(Project project) {
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        projectResponseDto.setId(project.getId());
        projectResponseDto.setName(project.getName());
        projectResponseDto.setMaxEmployee(project.getMaxEmployee());
        projectResponseDto.setMinEmployee(project.getMinEmployee());
        projectResponseDto.setTotalEmployee(project.getTotalEmployee());
        return projectResponseDto;
    }

    @Override
    public Project toEntitys(CreateProjectRequestDTO createProjectRequestDTO) {
        Project project = new Project();
        project.setName(createProjectRequestDTO.getName());
        project.setMinEmployee(createProjectRequestDTO.getMinEmployee());
        project.setMaxEmployee(createProjectRequestDTO.getMaxEmployee());
        project.setTotalEmployee(createProjectRequestDTO.getTotalEmployee());
        return project;
    }

    @Override
    public CreateProjectResponseDTO toRespons(Project project) {
        CreateProjectResponseDTO responseDTO = new CreateProjectResponseDTO();
        responseDTO.setId(project.getId());
        responseDTO.setName(project.getName());
        responseDTO.setMaxEmployee(project.getMaxEmployee());
        responseDTO.setMinEmployee(project.getMinEmployee());
        responseDTO.setTotalEmployee(project.getTotalEmployee());
        return responseDTO;
    }

    @Override
    public UpdateProjectResponseDTO toUpdateProjectResponse(Project project) {
        UpdateProjectResponseDTO responseDTO = new UpdateProjectResponseDTO();
        responseDTO.setId(project.getId());
        responseDTO.setName(project.getName());
        responseDTO.setMaxEmployee(project.getMaxEmployee());
        responseDTO.setMinEmployee(project.getMinEmployee());
        responseDTO.setTotalEmployee(project.getTotalEmployee());
        return responseDTO;
    }

    // user
    @Override
    public UserResponseDto toResponsed(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId().toString());
        responseDto.setPassword(user.getPassword());
        responseDto.setNickName(user.getNickName());
        return responseDto;
    }

    @Override
    public UpdateUserResponseDTO toUpdateUserResponse(User user) {
        UpdateUserResponseDTO responseDto = new UpdateUserResponseDTO();
        responseDto.setId(user.getId());
        responseDto.setNickName(user.getNickName());
        responseDto.setPassword(user.getPassword());
        return responseDto;
    }

    // employee
    @Override
    public EmployeeResponseDto toResponsee(Employee employee) {
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setProjectId(employee.getProject().getId());
        employeeResponseDto.setFullName(employee.getFullName());
        employeeResponseDto.setPosition(employee.getPosition());
        employeeResponseDto.setIdentityNumber(employee.getIdentityNumber());
        employeeResponseDto.setSalary(employee.getSalary());
        return employeeResponseDto;
    }

    @Override
    public UpdateEmployeeResponseDTO toUpdateEmployeeResponse(Employee employee) {
        UpdateEmployeeResponseDTO updateEmployeeResponseDTO = new UpdateEmployeeResponseDTO();
        updateEmployeeResponseDTO.setFullName(employee.getFullName());
        updateEmployeeResponseDTO.setPosition(employee.getPosition());
        updateEmployeeResponseDTO.setIdentityNumber(employee.getIdentityNumber());
        updateEmployeeResponseDTO.setSalary(employee.getSalary());
        return updateEmployeeResponseDTO;
    }
}
