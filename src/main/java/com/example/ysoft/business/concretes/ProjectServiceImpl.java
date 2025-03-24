package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.requests.project.UpdateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.*;
import com.example.ysoft.business.dtos.responses.project.*;
import com.example.ysoft.core.mapper.MapperService;
import com.example.ysoft.dataAccess.ProjectRepository;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.library.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperService mapperService;

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream().map(mapperService::toResponse).collect(Collectors.toList());
    }

    @Override
    public Project findById(String id) {
        UUID projectId = UUID.fromString(id);
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project Bulunamadı"));
    }

    @Override
    public CreateProjectResponseDTO addProject(CreateProjectRequestDTO createProjectRequestDTO) {
        Project project = mapperService.toEntitys(createProjectRequestDTO);

        Project savedProject = projectRepository.save(project);
        return mapperService.toRespons(savedProject);
    }


    @Override
    public ProjectResponseDto getById(String id) {
        UUID projectId = UUID.fromString(id);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project id bulunamadı"));
        return  mapperService.toResponse(project);
    }

    @Override
    public UpdateProjectResponseDTO updateProject(UpdateProjectRequestDTO updateProjectRequestDTO) {
        UUID projectId = updateProjectRequestDTO.getId();
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        existingProject.setName(updateProjectRequestDTO.getName());
        existingProject.setMaxEmployee(updateProjectRequestDTO.getMaxEmployee());
        existingProject.setMinEmployee(updateProjectRequestDTO.getMinEmployee());
        existingProject.setTotalEmployee(updateProjectRequestDTO.getTotalEmployee());

        Project updatedProject = projectRepository.save(existingProject);
        return mapperService.toUpdateProjectResponse(updatedProject);
    }

    @Override
    public void deleteProject(String id) {
        UUID projectId = UUID.fromString(id);
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<GetEmployeeNamesByProjectIdResponseDTO> getFindEmployeeNamesByProjectId(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        List<String> employeeNames = projectRepository.findEmployeeNamesByProjectId(projectUuid);

        return employeeNames.stream()
                .map(GetEmployeeNamesByProjectIdResponseDTO::new)
                .collect(Collectors.toList());
    }


    @Override
    public GetCountEmployeesByProjectIdResponseDTO getCountEmployeesByProjectId(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        Long count = projectRepository.countEmployeesByProjectId(projectUuid);

        return new GetCountEmployeesByProjectIdResponseDTO(count);
    }


    @Override
    public List<GetFindEmployeesByProjectIdResponseDTO> getFindEmployeesByProjectId(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        List<Employee> employees = projectRepository.findEmployeesByProjectId(projectUuid);

        List<GetFindEmployeesByProjectIdResponseDTO> dtoList = new ArrayList<>();
        //employeeden gelen listeyi response listesine mapleyeceğim ve bu responsu return edeceğim.
        for (Employee emp : employees) {
            dtoList.add(new GetFindEmployeesByProjectIdResponseDTO(
                    emp.getFullName(),
                    emp.getPosition(),
                    emp.getIdentityNumber(),
                    emp.getSalary()
            ));
        }
        return dtoList;
    }

}
