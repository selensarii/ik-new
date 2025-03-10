package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.requests.ProjectRequestDto;
import com.example.ysoft.dataAccess.ProjectRepository;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.library.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectManager implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public Project findById(String id) {
        UUID projectId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project Bulunamadı"));
    }

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        Project savedProject = projectRepository.save(toEntity(projectRequestDto));
        return toResponse(savedProject);
    }

    @Override
    public ProjectResponseDto getById(String id) {
        UUID projectId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project id bulunamadı"));
        return toResponse(project);
    }

    @Override
    public ProjectResponseDto updateProject(String id, ProjectRequestDto projectRequestDto) {
        UUID projectId = UUID.fromString(id); // String'i UUID'ye dönüştürme
        return projectRepository.findById(projectId)
                .map(existingProject -> {
                    Project updatedProject = toEntity(projectRequestDto);
                    updatedProject.setId(existingProject.getId());
                    return projectRepository.save(updatedProject);
                })
                .map(this::toResponse)
                .orElseThrow(() -> new ProjectNotFoundException("Project Güncellenemedi"));
    }

    @Override
    public void deleteProject(String id) {
        UUID projectId = UUID.fromString(id);
        projectRepository.deleteById(projectId);
    }
    @Override
    public List<String> getEmployeeNamesByProjectId(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        return projectRepository.findEmployeeNamesByProjectId(projectUuid);
    }

    @Override
    public Long getCountEmployeesByProjectId(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        return projectRepository.countEmployeesByProjectId(projectUuid);
    }
    @Override
    public List<Employee> getFindEmployeesByProjectId(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        return projectRepository.findEmployeesByProjectId(projectUuid);
    }

    private ProjectResponseDto toResponse(Project project) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .maxEmployee(project.getMaxEmployee())
                .minEmployee(project.getMinEmployee())
                .totalEmployee(project.getTotalEmployee())
                .build();
    }

    private Project toEntity(ProjectRequestDto projectRequestDto) {
        return Project.builder()
                .name(projectRequestDto.getName())
                .minEmployee(projectRequestDto.getMinEmployee())
                .maxEmployee(projectRequestDto.getMaxEmployee())
                .totalEmployee(projectRequestDto.getTotalEmployee())
                .build();
    }
}
