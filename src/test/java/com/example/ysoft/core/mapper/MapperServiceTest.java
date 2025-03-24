package com.example.ysoft.core.mapper;

import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.project.CreateProjectRequestDTO;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.ProjectResponseDto;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.project.CreateProjectResponseDTO;
import com.example.ysoft.business.dtos.responses.project.UpdateProjectResponseDTO;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapperServiceTest {

    @InjectMocks
    private MapperServiceImpl mapperService;

    @Mock
    private CreateEmployeeRequestDTO createEmployeeRequestDTO;
    @Mock
    private Project project;
    @Mock
    private CreateProjectRequestDTO createProjectRequestDTO;
    @Mock
    private User user;
    @Mock
    private CreateUserRequestDTO createUserRequestDTO;
    @Mock
    private Employee employee;


    @Test
    void testToCreateEmployeeResponse() {
        CreateEmployeeResponseDTO expected = new CreateEmployeeResponseDTO();
        CreateEmployeeResponseDTO actual = mapperService.toCreateEmployeeResponse(employee);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToEntityEmployee() {
        Employee expected = new Employee();
        Employee actual = mapperService.toEntity(createEmployeeRequestDTO, project);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToResponseProject() {
        ProjectResponseDto expected = new ProjectResponseDto();
        ProjectResponseDto actual = mapperService.toResponse(project);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToEntitysProject() {
        Project expected = new Project();
        Project actual = mapperService.toEntitys(createProjectRequestDTO);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToResponsProject() {
        CreateProjectResponseDTO expected = new CreateProjectResponseDTO();
        CreateProjectResponseDTO actual = mapperService.toRespons(project);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToUpdateProjectResponse() {
        UpdateProjectResponseDTO expected = new UpdateProjectResponseDTO();
        UpdateProjectResponseDTO actual = mapperService.toUpdateProjectResponse(project);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToResponsedUser() {
        UUID userId = UUID.randomUUID();

        when(user.getId()).thenReturn(userId);

        UserResponseDto expected = new UserResponseDto();
        UserResponseDto actual = mapperService.toResponsed(user);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToUpdateUserResponse() {
        UpdateUserResponseDTO expected = new UpdateUserResponseDTO();
        UpdateUserResponseDTO actual = mapperService.toUpdateUserResponse(user);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToResponseeEmployee() {
        UUID projectId = UUID.randomUUID();

        when(employee.getProject()).thenReturn(project);
        when(project.getId()).thenReturn(projectId);

        EmployeeResponseDto expected = new EmployeeResponseDto();
        EmployeeResponseDto actual = mapperService.toResponsee(employee);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void testToUpdateEmployeeResponse() {
        UpdateEmployeeResponseDTO expected = new UpdateEmployeeResponseDTO();
        UpdateEmployeeResponseDTO actual = mapperService.toUpdateEmployeeResponse(employee);
        assertEquals(expected.getClass(), actual.getClass());
    }
}