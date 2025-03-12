package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.requests.project.UpdateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.*;
import com.example.ysoft.business.dtos.requests.ProjectRequestDto;
import com.example.ysoft.business.dtos.responses.project.*;
import com.example.ysoft.entities.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    Project findById(String id);
    ProjectResponseDto getById(String id);
    void deleteProject(String id);

    UpdateProjectResponseDTO updateProject(UpdateProjectRequestDTO updateProjectRequestDTO);
    CreateProjectResponseDTO addProject(CreateProjectRequestDTO createProjectRequestDTO);
    List<GetEmployeeNamesByProjectIdResponseDTO> getFindEmployeeNamesByProjectId(String projectId);
    GetCountEmployeesByProjectIdResponseDTO getCountEmployeesByProjectId(String projectId);
    List<GetFindEmployeesByProjectIdResponseDTO> getFindEmployeesByProjectId(String projectId);


}
