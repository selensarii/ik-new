package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.requests.project.UpdateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.responses.project.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Test
    void test_getAllProjects() {
        ProjectResponseDto projectResponseDto1 = new ProjectResponseDto();
        projectResponseDto1.setId(UUID.randomUUID());
        projectResponseDto1.setName("Project 1");

        ProjectResponseDto projectResponseDto2 = new ProjectResponseDto();
        projectResponseDto2.setId(UUID.randomUUID());
        projectResponseDto2.setName("Project 2");

        List<ProjectResponseDto> expected = Arrays.asList(projectResponseDto1, projectResponseDto2);

        when(projectService.getAllProjects()).thenReturn(expected);

        List<ProjectResponseDto> actual = projectController.getAllProjects();

        assertEquals(expected, actual);
    }

    @Test
    void test_addProject() {
        CreateProjectRequestDTO createProjectRequestDTO = new CreateProjectRequestDTO("New Project",   12L,6L,5L );


        CreateProjectResponseDTO expected = new CreateProjectResponseDTO();
        expected.setId(UUID.randomUUID());
        expected.setName("New Project");

        when(projectService.addProject(createProjectRequestDTO)).thenReturn(expected);

        CreateProjectResponseDTO actual = projectController.addProject(createProjectRequestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_getById() {
        UUID uuid = UUID.randomUUID();
        String projectId = uuid.toString();
        ProjectResponseDto expected = new ProjectResponseDto();
        expected.setId(uuid);
        expected.setName("alkaravli");

        when(projectService.getById(projectId)).thenReturn(expected);

        ProjectResponseDto actual = projectController.getById(projectId);

        assertEquals(expected, actual);
    }

    @Test
    void test_updateProject() {
        UUID projectId = UUID.randomUUID();
        UpdateProjectRequestDTO updateProjectRequestDTO = new UpdateProjectRequestDTO(
                projectId,
                "alkaravli",
                10L,
                5L,
                8L
        );

        UpdateProjectResponseDTO expected = new UpdateProjectResponseDTO(
                projectId,
                "alkaravli",
                10L,
                5L,
                8L
        );

        when(projectService.updateProject(updateProjectRequestDTO)).thenReturn(expected);

        UpdateProjectResponseDTO actual = projectController.updateProject(updateProjectRequestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_deleteProject() {
        String projectId = UUID.randomUUID().toString();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Proje başarıyla silindi: " + projectId, HttpStatus.OK);

        ResponseEntity<String> actualResponse = projectController.deleteProject(projectId);

        assertEquals(expectedResponse, actualResponse);
        verify(projectService, times(1)).deleteProject(projectId);
    }

    @Test
    void test_getEmployeeNamesByProjectId() {
        String projectId = UUID.randomUUID().toString();
        GetEmployeeNamesByProjectIdResponseDTO responseDTO1 = new GetEmployeeNamesByProjectIdResponseDTO("Employee 1");
        GetEmployeeNamesByProjectIdResponseDTO responseDTO2 = new GetEmployeeNamesByProjectIdResponseDTO("Employee 2");
        List<GetEmployeeNamesByProjectIdResponseDTO> expected = Arrays.asList(responseDTO1, responseDTO2);

        when(projectService.getFindEmployeeNamesByProjectId(projectId)).thenReturn(expected);

        List<GetEmployeeNamesByProjectIdResponseDTO> actual = projectController.getEmployeeNamesByProjectId(projectId);

        assertEquals(expected, actual);
    }

    @Test
    void test_getCountEmployeesByProjectId() {
        String projectId = UUID.randomUUID().toString();
        GetCountEmployeesByProjectIdResponseDTO expected = new GetCountEmployeesByProjectIdResponseDTO(5L);

        when(projectService.getCountEmployeesByProjectId(projectId)).thenReturn(expected);

        GetCountEmployeesByProjectIdResponseDTO actual = projectController.getCountEmployeesByProjectId(projectId);

        assertEquals(expected, actual);
    }

    @Test
    void test_getFindEmployeesByProjectId() {
        String projectId = UUID.randomUUID().toString();
        GetFindEmployeesByProjectIdResponseDTO responseDTO1 = new GetFindEmployeesByProjectIdResponseDTO("Employee 1", "Developer", "123", "5000.0");
        GetFindEmployeesByProjectIdResponseDTO responseDTO2 = new GetFindEmployeesByProjectIdResponseDTO("Employee 2", "Tester", "456", "6000.0");
        List<GetFindEmployeesByProjectIdResponseDTO> expected = Arrays.asList(responseDTO1, responseDTO2);

        when(projectService.getFindEmployeesByProjectId(projectId)).thenReturn(expected);

        List<GetFindEmployeesByProjectIdResponseDTO> actual = projectController.getFindEmployeesByProjectId(projectId);

        assertEquals(expected, actual);
    }
}