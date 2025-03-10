package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.ProjectRequestDto;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.entities.Employee;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ProjectResponseDto addProject(@RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.addProject(projectRequestDto);
    }

    @GetMapping("/{id}")
    public ProjectResponseDto getById(@PathVariable(value = "id") String id) {
        return projectService.getById(id);
    }

    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(@PathVariable(name = "id") String id, @RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.updateProject(id, projectRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable(name = "id") String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Proje başarıyla silindi: " + id);
    }
    @GetMapping("/{projectId}/employees")
    public List<String> getEmployeeNamesByProjectId(@PathVariable String projectId) {
        return projectService.getEmployeeNamesByProjectId(projectId);
    }
    @GetMapping("/{projectId}/employeeCount")
    public Long getCountEmployeesByProjectId(@PathVariable String projectId) {
        return projectService.getCountEmployeesByProjectId(projectId);
    }

    @GetMapping("/{projectId}/employeesAll")
    public List<Employee> getFindEmployeesByProjectId(@PathVariable String projectId) {
        return projectService.getFindEmployeesByProjectId(projectId);
    }


}
