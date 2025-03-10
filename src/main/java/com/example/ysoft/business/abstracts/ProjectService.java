package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.requests.ProjectRequestDto;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    Project findById(String id);
    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);
    ProjectResponseDto getById(String id);
    ProjectResponseDto updateProject(String id, ProjectRequestDto projectRequestDto);
    void deleteProject(String id);
    List<String> getEmployeeNamesByProjectId(String projectId);
    Long getCountEmployeesByProjectId(String projectId);
    List<Employee> getFindEmployeesByProjectId(String projectId);
}
