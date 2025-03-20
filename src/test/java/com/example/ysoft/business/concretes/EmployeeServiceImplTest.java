package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.dataAccess.EmployeeRepository;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.library.EmployeeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private UUID employeeId;
    private Employee employee;
    private Project project;

    @BeforeEach
    void setUp() {
        employeeId = UUID.randomUUID();
        project = new Project();
        project.setId(UUID.randomUUID());
        employee = Employee.builder()
                .fullName("selen sari")
                .position("Developer")
                .identityNumber("12345")
                .salary("6000.0")
                .project(project)
                .build();
    }

    @Test
    void test_addEmployee() {
        // Arrange
        CreateEmployeeRequestDTO createEmployeeRequestDTO = new CreateEmployeeRequestDTO();
        createEmployeeRequestDTO.setFullName("selen sari");
        createEmployeeRequestDTO.setPosition("Developer");
        createEmployeeRequestDTO.setIdentityNumber("12345");
        createEmployeeRequestDTO.setSalary("5000.0");
        createEmployeeRequestDTO.setProjectId(project.getId());

        when(projectService.findById(anyString())).thenReturn(project);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        CreateEmployeeResponseDTO actual = employeeService.addEmployee(createEmployeeRequestDTO); //buraya hash code ekleyince düzeldi?

        CreateEmployeeResponseDTO expected = new CreateEmployeeResponseDTO(employee.getId(), employee.getFullName(), employee.getPosition(), employee.getIdentityNumber(), employee.getSalary(), employee.getProject().getId());
        assertEquals(expected, actual);
    }

    @Test
    void test_getById() { //her şeyi aynı çıktı ama hata veriyor EmployeeResponseDto içine hash code yazınca düzeldi?
        // Arrange
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Act
        EmployeeResponseDto actual = employeeService.getById(employeeId.toString());
        EmployeeResponseDto expected = new EmployeeResponseDto();
        expected.setFullName(employee.getFullName());
        expected.setIdentityNumber(employee.getIdentityNumber());
        expected.setSalary(employee.getSalary());
        expected.setProjectId(employee.getProject().getId());
        expected.setPosition(employee.getPosition());

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void test_updateEmployee() throws JsonProcessingException {
        // Arrange
        UpdateEmployeeRequestDTO updateEmployeeRequestDTO = new UpdateEmployeeRequestDTO(employeeId, "selen sari", "Senior Developer", "12345", "6000.0", project.getId());
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(projectService.findById(anyString())).thenReturn(project);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        UpdateEmployeeResponseDTO updatedEmployeeResponseDTO = employeeService.updateEmployee(updateEmployeeRequestDTO);

        // Assert
        assertEquals("selen sari", updatedEmployeeResponseDTO.getFullName());
        assertEquals("Senior Developer", updatedEmployeeResponseDTO.getPosition());
        assertEquals(6000.0, Double.valueOf(updatedEmployeeResponseDTO.getSalary())); //double dönüştürmediğimde hata aldım
    }

    @Test
    void test_deleteEmployee() { //gpt çözdü gereksiz mockları kaldırdı
        // Act
        employeeService.deleteEmployee(employeeId.toString());

        // Assert
        verify(employeeRepository, times(1)).deleteById(eq(employeeId));
    }


    @Test
    void test_getFindEmployeeFullNameById() {
        // Arrange
        when(employeeRepository.findEmployeeFullNameById(employeeId)).thenReturn(employee.getFullName());

        // Act
        String actual = employeeService.getFindEmployeeFullNameById(employeeId.toString());

        // Assert
        assertEquals(employee.getFullName(), actual);
    }

}
