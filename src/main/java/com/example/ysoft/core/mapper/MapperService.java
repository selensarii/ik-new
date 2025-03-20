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

public interface MapperService {
    CreateEmployeeResponseDTO toCreateEmployeeResponse(Employee savedEmployee);
    Employee toEntity(CreateEmployeeRequestDTO createEmployeeRequestDTO, Project project);
    ProjectResponseDto toResponse(Project project);
    Project toEntitys(CreateProjectRequestDTO createProjectRequestDTO);
    CreateProjectResponseDTO toRespons(Project project);
    UpdateProjectResponseDTO toUpdateProjectResponse(Project project);
    UserResponseDto toResponsed(User user);
    UpdateUserResponseDTO toUpdateUserResponse(User user);
    EmployeeResponseDto toResponsee(Employee employee);
    UpdateEmployeeResponseDTO toUpdateEmployeeResponse(Employee employee);

}

