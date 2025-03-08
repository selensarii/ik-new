package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.responses.ProjectResponse;
import com.example.ysoft.business.dtos.requests.ProjectRequest;
import com.example.ysoft.entities.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> getAllProjects();
    Project findById(String id);
    ProjectResponse addProject(ProjectRequest projectRequest);
    ProjectResponse getById(String id);
    ProjectResponse updateProject(String id, ProjectRequest projectRequest);
    void deleteProject(String id);

}
