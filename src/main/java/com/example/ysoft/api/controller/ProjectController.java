package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.ProjectRequest;
import com.example.ysoft.business.dtos.responses.ProjectResponse;
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
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ProjectResponse addProject(@RequestBody ProjectRequest projectRequest) {
        return projectService.addProject(projectRequest);
    }

    @GetMapping("/{id}")
    public ProjectResponse getById(@PathVariable(value = "id") String id) {
        return projectService.getById(id);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateProject(@PathVariable(name = "id") String id, @RequestBody ProjectRequest projectRequest) {
        return projectService.updateProject(id, projectRequest);
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
