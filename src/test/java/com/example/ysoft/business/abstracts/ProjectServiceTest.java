package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.requests.project.UpdateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.responses.project.*;
import com.example.ysoft.entities.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest {
    @Mock
    private ProjectService projectService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAllProjects() {
        // Given
        ProjectResponseDto projectResponse = new ProjectResponseDto();
        UUID projectId = UUID.randomUUID();
        projectResponse.setId(projectId);
        projectResponse.setName("Test Project");

        List<ProjectResponseDto> expected = List.of(projectResponse);
        when(projectService.getAllProjects()).thenReturn(expected);

        // When
        List<ProjectResponseDto> actual = projectService.getAllProjects();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_getById() {
        // Given
        UUID projectId = UUID.randomUUID();
        ProjectResponseDto expected = new ProjectResponseDto();
        expected.setId(projectId);
        expected.setName("Test Project");

        when(projectService.getById(projectId.toString())).thenReturn(expected);

        // When
        ProjectResponseDto actual = projectService.getById(projectId.toString());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_findById() {
        // Given
        UUID projectId = UUID.randomUUID();
        Project expected = new Project();
        expected.setId(projectId);
        expected.setName("Test Project");

        when(projectService.findById(projectId.toString())).thenReturn(expected);

        // When
        Project actual = projectService.findById(projectId.toString());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_addProject() {
        // Given
        CreateProjectRequestDTO requestDTO = new CreateProjectRequestDTO("proje1", 20L, 10L, 7L);

        UUID projectId = UUID.randomUUID();
        CreateProjectResponseDTO expected = new CreateProjectResponseDTO(projectId, "proje1", 20L, 10L, 7L);

        when(projectService.addProject(requestDTO)).thenReturn(expected);

        // When
        CreateProjectResponseDTO actual = projectService.addProject(requestDTO);

        // Then
        assertEquals(expected, actual);
    }


    @Test
    void test_updateProject() {
        // Given
        UUID projectId = UUID.randomUUID();
        UpdateProjectRequestDTO requestDTO = new UpdateProjectRequestDTO(projectId,"proje1",20L,10L,7L);

        UpdateProjectResponseDTO expected = new UpdateProjectResponseDTO(projectId,"proje1",20L,10L,7L);

        when(projectService.updateProject(requestDTO)).thenReturn(expected);

        // When
        UpdateProjectResponseDTO actual = projectService.updateProject(requestDTO);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_getFindEmployeeNamesByProjectId() {
        // Given
        UUID projectId = UUID.randomUUID();
        GetEmployeeNamesByProjectIdResponseDTO employeeResponse = new GetEmployeeNamesByProjectIdResponseDTO("selensari");

        List<GetEmployeeNamesByProjectIdResponseDTO> expected = List.of(employeeResponse);
        when(projectService.getFindEmployeeNamesByProjectId(projectId.toString())).thenReturn(expected);

        // When
        List<GetEmployeeNamesByProjectIdResponseDTO> actual = projectService.getFindEmployeeNamesByProjectId(projectId.toString());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_getCountEmployeesByProjectId() {
        // Given
        UUID projectId = UUID.randomUUID();
        GetCountEmployeesByProjectIdResponseDTO expected = new GetCountEmployeesByProjectIdResponseDTO(5L);

        when(projectService.getCountEmployeesByProjectId(projectId.toString())).thenReturn(expected);

        // When
        GetCountEmployeesByProjectIdResponseDTO actual = projectService.getCountEmployeesByProjectId(projectId.toString());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_getFindEmployeesByProjectId() {
        // Given
        UUID projectId = UUID.randomUUID();
        GetFindEmployeesByProjectIdResponseDTO employeeResponse = new GetFindEmployeesByProjectIdResponseDTO("selensari","developer","2551515125","5000");
        List<GetFindEmployeesByProjectIdResponseDTO> expected = List.of(employeeResponse);
        when(projectService.getFindEmployeesByProjectId(projectId.toString())).thenReturn(expected);

        // When
        List<GetFindEmployeesByProjectIdResponseDTO> actual = projectService.getFindEmployeesByProjectId(projectId.toString());

        // Then
        assertEquals(expected, actual);
    }

}
