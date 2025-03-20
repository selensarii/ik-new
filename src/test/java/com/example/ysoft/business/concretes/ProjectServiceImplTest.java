package com.example.ysoft.business.concretes;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.ysoft.dataAccess.ProjectRepository;
import com.example.ysoft.entities.Project;
import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.project.CreateProjectResponseDTO;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.requests.project.UpdateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.project.UpdateProjectResponseDTO;
import com.example.ysoft.library.ProjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private CreateProjectRequestDTO createProjectRequestDTO;
    private UUID projectId;

    @BeforeEach
    public void setUp() {
        projectRepository = mock(ProjectRepository.class);
        projectService = new ProjectServiceImpl(projectRepository);

        projectId = UUID.randomUUID();
        project = new Project();
        project.setId(projectId);
        project.setName("Project 1");
        project.setMinEmployee(1L);
        project.setMaxEmployee(5L);
        project.setTotalEmployee(3L);

        createProjectRequestDTO = new CreateProjectRequestDTO("Project 1", 5L, 1L, 3L);
    }

    @Test
    public void testAddProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        CreateProjectResponseDTO actual = projectService.addProject(createProjectRequestDTO);

        CreateProjectResponseDTO expected = new CreateProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getMaxEmployee(),
                project.getMinEmployee(),
                project.getTotalEmployee()
        );

        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        ProjectResponseDto actual = projectService.getById(projectId.toString());

        ProjectResponseDto expected = new ProjectResponseDto(
                project.getId(),
                project.getName(),
                project.getMaxEmployee(),
                project.getMinEmployee(),
                project.getTotalEmployee()
        );

        assertEquals(expected, actual);
    }


    @Test
    public void testUpdateProject() {
        UUID projectId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UpdateProjectRequestDTO updateRequestDTO = new UpdateProjectRequestDTO(projectId, "Updated Project", 10L, 2L, 7L);
        updateRequestDTO.setId(projectId);
        updateRequestDTO.setName("Updated Project");
        updateRequestDTO.setMaxEmployee(10L);
        updateRequestDTO.setMinEmployee(2L);
        updateRequestDTO.setTotalEmployee(7L);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        UpdateProjectResponseDTO actual = projectService.updateProject(updateRequestDTO);

        UpdateProjectResponseDTO expected = new UpdateProjectResponseDTO(
                project.getId(),
                "Updated Project",
                10L,
                2L,
                7L
        );

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getMaxEmployee(), actual.getMaxEmployee());
        assertEquals(expected.getMinEmployee(), actual.getMinEmployee());
        assertEquals(expected.getTotalEmployee(), actual.getTotalEmployee());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        String id = "b5d73e1d-ff4a-4b74-b678-b70f6d87a9a7"; // Example UUID
        UUID employeeId = UUID.fromString(id);

        // Act
        projectService.deleteProject(id);

        // Assert
        verify(projectRepository, times(1)).deleteById(employeeId);
    }

}
