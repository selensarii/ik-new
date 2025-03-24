package com.example.ysoft.business.concretes;

import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.requests.project.UpdateProjectRequestDTO;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.responses.project.CreateProjectResponseDTO;
import com.example.ysoft.business.dtos.responses.project.GetCountEmployeesByProjectIdResponseDTO;
import com.example.ysoft.business.dtos.responses.project.GetFindEmployeesByProjectIdResponseDTO;
import com.example.ysoft.business.dtos.responses.project.UpdateProjectResponseDTO;
import com.example.ysoft.core.mapper.MapperService;
import com.example.ysoft.dataAccess.ProjectRepository;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private MapperService mapperService; // MapperService arayüzünü mockluyoruz

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void test_getAllProjects() {
        Project project1 = new Project();
        Project project2 = new Project();
        List<Project> projects = Arrays.asList(project1, project2);

        ProjectResponseDto responseDto1 = new ProjectResponseDto();
        ProjectResponseDto responseDto2 = new ProjectResponseDto();
        List<ProjectResponseDto> expected = Arrays.asList(responseDto1, responseDto2);

        when(projectRepository.findAll()).thenReturn(projects);
        when(mapperService.toResponse(project1)).thenReturn(responseDto1);
        when(mapperService.toResponse(project2)).thenReturn(responseDto2);

        List<ProjectResponseDto> actual = projectService.getAllProjects();

        assertEquals(expected, actual);
    }

    @Test
    void test_findById() {
        UUID projectId = UUID.randomUUID();
        Project expected = new Project();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(expected));

        Project actual = projectService.findById(projectId.toString());

        assertEquals(expected, actual);
    }

    @Test
    void test_addProject() {
        CreateProjectRequestDTO requestDTO = new CreateProjectRequestDTO("alkaravli", 10L, 8L, 9L);
        Project project = new Project();
        Project savedProject = new Project();
        CreateProjectResponseDTO expected = new CreateProjectResponseDTO();

        when(mapperService.toEntitys(requestDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(savedProject);
        when(mapperService.toRespons(savedProject)).thenReturn(expected);

        CreateProjectResponseDTO actual = projectService.addProject(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_getById() {
        UUID projectId = UUID.randomUUID();
        Project project = new Project();
        ProjectResponseDto expected = new ProjectResponseDto();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(mapperService.toResponse(project)).thenReturn(expected);

        ProjectResponseDto actual = projectService.getById(projectId.toString());

        assertEquals(expected, actual);
    }

    @Test
    void test_updateProject() {
        UUID projectId = UUID.randomUUID();
        UpdateProjectRequestDTO requestDTO = new UpdateProjectRequestDTO(projectId, "alkaravli", 18L, 8L, 11L);
        requestDTO.setId(projectId);

        Project existingProject = new Project();
        Project updatedProject = new Project();
        UpdateProjectResponseDTO expected = new UpdateProjectResponseDTO();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(existingProject)).thenReturn(updatedProject);
        when(mapperService.toUpdateProjectResponse(updatedProject)).thenReturn(expected);

        UpdateProjectResponseDTO actual = projectService.updateProject(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_deleteProject() {
        UUID projectId = UUID.randomUUID();

        projectService.deleteProject(projectId.toString());

        verify(projectRepository).deleteById(projectId);
    }

    @Test
    void test_getCountEmployeesByProjectId() {
        UUID projectId = UUID.randomUUID();
        Long count = 5L;
        GetCountEmployeesByProjectIdResponseDTO expectedResponse = new GetCountEmployeesByProjectIdResponseDTO(count);

        when(projectRepository.countEmployeesByProjectId(projectId)).thenReturn(count);

        GetCountEmployeesByProjectIdResponseDTO actualResponse = projectService.getCountEmployeesByProjectId(projectId.toString());

        assertEquals(expectedResponse.getCount(), actualResponse.getCount());
    }

    @Test
    void test_getFindEmployeesByProjectId() {
        UUID projectId = UUID.randomUUID();
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", "Developer", "123456789", "5000.0", null),
                new Employee("Jane Smith", "Tester", "987654321", "6000.0", null)
        );

        List<GetFindEmployeesByProjectIdResponseDTO> expectedResponse = employees.stream()
                .map(emp -> new GetFindEmployeesByProjectIdResponseDTO(
                        emp.getFullName(),
                        emp.getPosition(),
                        emp.getIdentityNumber(),
                        emp.getSalary()
                ))
                .collect(Collectors.toList());

        when(projectRepository.findEmployeesByProjectId(projectId)).thenReturn(employees);

        List<GetFindEmployeesByProjectIdResponseDTO> actualResponse = projectService.getFindEmployeesByProjectId(projectId.toString());

        for (int i = 0; i < expectedResponse.size(); i++) { //tek tek elle yapmam gerekti yoksa response içinde aynı şeyi yapmamı istiyordu
            assertEquals(expectedResponse.get(i).getFullName(), actualResponse.get(i).getFullName());
            assertEquals(expectedResponse.get(i).getPosition(), actualResponse.get(i).getPosition());
            assertEquals(expectedResponse.get(i).getIdentityNumber(), actualResponse.get(i).getIdentityNumber());
            assertEquals(expectedResponse.get(i).getSalary(), actualResponse.get(i).getSalary());
        }
    }
}