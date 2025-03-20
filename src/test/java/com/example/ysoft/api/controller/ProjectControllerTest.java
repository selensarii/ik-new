package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.project.*;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.responses.project.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;


    @Test
    void test_getAllProjects() throws Exception {
        // Given
        ProjectResponseDto projectResponse = new ProjectResponseDto();
        projectResponse.setId(UUID.randomUUID());
        projectResponse.setName("Test Project");

        List<ProjectResponseDto> expected = List.of(projectResponse);
        when(projectService.getAllProjects()).thenReturn(List.of(projectResponse));

        // When
        List<ProjectResponseDto> actual = projectController.getAllProjects();

        // Then
        assertEquals(expected, actual);
    }


    @Test
    void test_addProject() throws Exception {
        CreateProjectRequestDTO requestDTO = new CreateProjectRequestDTO(
                "Test Project", 100L, 10L, 50L);

        UUID projectId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        CreateProjectResponseDTO expected = new CreateProjectResponseDTO(
                projectId,
                "Test Project",
                100L,
                10L,
                50L
        );

        when(projectService.addProject(requestDTO)).thenReturn(expected);

        CreateProjectResponseDTO actual = projectController.addProject(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_getProjectById() throws Exception {
        // Given
        ProjectResponseDto responseDto = new ProjectResponseDto();
        when(projectService.getById("projectId")).thenReturn(responseDto);
        ProjectResponseDto actual = projectController.getById("projectId");

      assertEquals(responseDto, actual);
    }

    @Test
    void test_updateProject() throws Exception {
        // Given
        UUID validUUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        UpdateProjectRequestDTO requestDTO = new UpdateProjectRequestDTO(
                validUUID,
                "Updated Project",
                10L,
                5L,
                8L
        );


        UpdateProjectResponseDTO expected = new UpdateProjectResponseDTO(
                validUUID,
                "Updated Project",
                10L,
                5L,
                8L
        );

        when(projectService.updateProject(requestDTO)).thenReturn(expected);
        UpdateProjectResponseDTO actual = projectController.updateProject(requestDTO);
        assertEquals(expected,actual);

    }

    @Test
    void test_deleteProject() throws Exception {
        // Given
        doNothing().when(projectService).deleteProject("projectId");

        // When & Then
        mockMvc.perform(delete("/projects/v1/projectId/{projectId}", "projectId"))
                .andExpect(status().isOk())
                .andExpect(content().string("Proje başarıyla silindi: projectId"));
    }

    @Test
    void test_getEmployeeNamesByProjectId() throws Exception {
        List<GetEmployeeNamesByProjectIdResponseDTO> expected = List.of(new GetEmployeeNamesByProjectIdResponseDTO("Selen Sarı"));

        when(projectService.getFindEmployeeNamesByProjectId("projectId")).thenReturn(expected);

        List<GetEmployeeNamesByProjectIdResponseDTO> actual = projectController.getEmployeeNamesByProjectId("projectId");

        assertEquals(expected, actual);
    }


    @Test
    void test_getEmployeeCountByProjectId() throws Exception {
        GetCountEmployeesByProjectIdResponseDTO expected = new GetCountEmployeesByProjectIdResponseDTO(5L);
        when(projectService.getCountEmployeesByProjectId("projectId")).thenReturn(expected);
        GetCountEmployeesByProjectIdResponseDTO actual = projectController.getCountEmployeesByProjectId("projectId");
        assertEquals(expected,actual);

    }


    @Test
    void test_getEmployeesByProjectId() throws Exception {
        // Given
        GetFindEmployeesByProjectIdResponseDTO employee1 = new GetFindEmployeesByProjectIdResponseDTO("John Doe", "Software Engineer", "123456789", "$5000");
        GetFindEmployeesByProjectIdResponseDTO employee2 = new GetFindEmployeesByProjectIdResponseDTO("Jane Doe", "Project Manager", "987654321", "$6000");

        List<GetFindEmployeesByProjectIdResponseDTO> expected = List.of(employee1, employee2);

        when(projectService.getFindEmployeesByProjectId("projectId")).thenReturn(expected);

        List<GetFindEmployeesByProjectIdResponseDTO> actual = projectController.getFindEmployeesByProjectId("projectId");

        assertEquals(expected, actual);
    }

}

