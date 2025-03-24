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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapperServiceImplTest {

    @InjectMocks
    private MapperServiceImpl mapperService;

    @Mock
    private CreateEmployeeRequestDTO createEmployeeRequestDTO;

    @Mock
    private Project project;

    @Mock
    private Employee employee;

    @Mock
    private CreateProjectRequestDTO createProjectRequestDTO;

    @Mock
    private User user;

    @Mock
    private CreateUserRequestDTO createUserRequestDTO;

    @Test
    void testToCreateEmployeeResponse() {
        UUID employeeId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        when(employee.getId()).thenReturn(employeeId);
        when(employee.getFullName()).thenReturn("John Doe");
        when(employee.getPosition()).thenReturn("Developer");
        when(employee.getIdentityNumber()).thenReturn("123456789");
        when(employee.getSalary()).thenReturn("5000.0");
        when(employee.getProject()).thenReturn(project);
        when(project.getId()).thenReturn(projectId);

        CreateEmployeeResponseDTO responseDTO = mapperService.toCreateEmployeeResponse(employee);

        assertEquals(employeeId, responseDTO.getId());
        assertEquals("John Doe", responseDTO.getFullName());
        assertEquals("Developer", responseDTO.getPosition());
        assertEquals("123456789", responseDTO.getIdentityNumber());
        assertEquals("5000.0", responseDTO.getSalary());
        assertEquals(projectId, responseDTO.getProjectId());
    }

    @Test
    void testToEntityEmployee() {
        when(createEmployeeRequestDTO.getFullName()).thenReturn("John Doe");
        when(createEmployeeRequestDTO.getPosition()).thenReturn("Developer");
        when(createEmployeeRequestDTO.getIdentityNumber()).thenReturn("123456789");
        when(createEmployeeRequestDTO.getSalary()).thenReturn("5000.0");

        Employee employee = mapperService.toEntity(createEmployeeRequestDTO, project);

        assertEquals("John Doe", employee.getFullName());
        assertEquals("Developer", employee.getPosition());
        assertEquals("123456789", employee.getIdentityNumber());
        assertEquals("5000.0", employee.getSalary());
        assertEquals(project, employee.getProject());
    }

    @Test
    void testToResponseEntityProject() {
        UUID projectId = UUID.randomUUID();
        when(project.getId()).thenReturn(projectId);
        when(project.getName()).thenReturn("Project A");
        when(project.getMaxEmployee()).thenReturn(10L);
        when(project.getMinEmployee()).thenReturn(5L);
        when(project.getTotalEmployee()).thenReturn(8L);

        ProjectResponseDto responseDto = mapperService.toResponse(project);

        assertEquals(projectId, responseDto.getId());
        assertEquals("Project A", responseDto.getName());
        assertEquals(10L, responseDto.getMaxEmployee());
        assertEquals(5L, responseDto.getMinEmployee());
        assertEquals(8L, responseDto.getTotalEmployee());
    }

    @Test
    void testToEntitysProject() {
        when(createProjectRequestDTO.getName()).thenReturn("Project A");
        when(createProjectRequestDTO.getMaxEmployee()).thenReturn(10L);
        when(createProjectRequestDTO.getMinEmployee()).thenReturn(5L);
        when(createProjectRequestDTO.getTotalEmployee()).thenReturn(8L);

        Project project = mapperService.toEntitys(createProjectRequestDTO);

        assertEquals("Project A", project.getName());
        assertEquals(10L, project.getMaxEmployee());
        assertEquals(5L, project.getMinEmployee());
        assertEquals(8L, project.getTotalEmployee());
    }

    @Test
    void testToResponsProject() {
        UUID projectId = UUID.randomUUID();
        when(project.getId()).thenReturn(projectId);
        when(project.getName()).thenReturn("Project A");
        when(project.getMaxEmployee()).thenReturn(10L);
        when(project.getMinEmployee()).thenReturn(5L);
        when(project.getTotalEmployee()).thenReturn(8L);

        CreateProjectResponseDTO responseDto = mapperService.toRespons(project);

        assertEquals(projectId, responseDto.getId());
        assertEquals("Project A", responseDto.getName());
        assertEquals(10L, responseDto.getMaxEmployee());
        assertEquals(5L, responseDto.getMinEmployee());
        assertEquals(8L, responseDto.getTotalEmployee());
    }

    @Test
    void testToUpdateProjectResponse() {
        UUID projectId = UUID.randomUUID();
        when(project.getId()).thenReturn(projectId);
        when(project.getName()).thenReturn("Project A");
        when(project.getMaxEmployee()).thenReturn(10L);
        when(project.getMinEmployee()).thenReturn(5L);
        when(project.getTotalEmployee()).thenReturn(8L);

        UpdateProjectResponseDTO responseDto = mapperService.toUpdateProjectResponse(project);

        assertEquals(projectId, responseDto.getId());
        assertEquals("Project A", responseDto.getName());
        assertEquals(10L, responseDto.getMaxEmployee());
        assertEquals(5L, responseDto.getMinEmployee());
        assertEquals(8L, responseDto.getTotalEmployee());
    }

    @Test
    void testToResponsedUser() {
        UUID userId = UUID.randomUUID();
        when(user.getId()).thenReturn(userId);
        when(user.getNickName()).thenReturn("testUser");
        when(user.getPassword()).thenReturn("testPass");

        UserResponseDto responseDto = mapperService.toResponsed(user);

        assertEquals(userId.toString(), responseDto.getId());
        assertEquals("testUser", responseDto.getNickName());
        assertEquals("testPass", responseDto.getPassword());
    }

    @Test
    void testToUpdateUserResponse() {
        UUID userId = UUID.randomUUID();
        when(user.getId()).thenReturn(userId);
        when(user.getNickName()).thenReturn("testUser");
        when(user.getPassword()).thenReturn("testPass");

        UpdateUserResponseDTO responseDto = mapperService.toUpdateUserResponse(user);

        assertEquals(userId, responseDto.getId());
        assertEquals("testUser", responseDto.getNickName());
        assertEquals("testPass", responseDto.getPassword());
    }
    @Test
    void testToResponseeEmployee() {
        UUID employeeId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        when(employee.getId()).thenReturn(employeeId);
        when(employee.getProject()).thenReturn(project);
        when(project.getId()).thenReturn(projectId);
        when(employee.getFullName()).thenReturn("Test Employee");
        when(employee.getPosition()).thenReturn("Developer");
        when(employee.getIdentityNumber()).thenReturn("1234567890"); // Tamamlandı
        when(employee.getSalary()).thenReturn("6000.0");

        EmployeeResponseDto responseDto = mapperService.toResponsee(employee);

        assertEquals(employeeId, responseDto.getId());
        assertEquals(projectId, responseDto.getProjectId());
        assertEquals("Test Employee", responseDto.getFullName());
        assertEquals("Developer", responseDto.getPosition());
        assertEquals("1234567890", responseDto.getIdentityNumber());
        assertEquals("6000.0", responseDto.getSalary());
    }

    @Test
    void testToUpdateEmployeeResponse() {
        when(employee.getFullName()).thenReturn("Updated Employee");
        when(employee.getPosition()).thenReturn("Senior Developer");
        when(employee.getIdentityNumber()).thenReturn("0987654321");
        when(employee.getSalary()).thenReturn("7000.0");

        UpdateEmployeeResponseDTO responseDto = mapperService.toUpdateEmployeeResponse(employee);

        assertEquals("Updated Employee", responseDto.getFullName());
        assertEquals("Senior Developer", responseDto.getPosition());
        assertEquals("0987654321", responseDto.getIdentityNumber());
        assertEquals("7000.0", responseDto.getSalary());
    }

    @Test
    void testToUserEntity() {
        when(createUserRequestDTO.getNickName()).thenReturn("testUser");
        when(createUserRequestDTO.getPassword()).thenReturn("testPass");

        User user = mapperService.toUserEntity(createUserRequestDTO);

        assertEquals("testUser", user.getNickName());
        assertEquals("testPass", user.getPassword());
    }

    @Test
    void testToUserResponse() {
        UUID userId = UUID.randomUUID();
        when(user.getId()).thenReturn(userId);
        when(user.getNickName()).thenReturn("testUser");
        when(user.getPassword()).thenReturn("testPass");

        CreateUserResponseDTO responseDto = mapperService.toUserResponse(user);

        assertEquals(userId, responseDto.getId());
        assertEquals("testUser", responseDto.getNickName());
        assertEquals("testPass", responseDto.getPassword());
    }
}