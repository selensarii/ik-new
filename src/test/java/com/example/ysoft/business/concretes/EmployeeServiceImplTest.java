package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.ProjectService;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.core.mapper.MapperService;
import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import com.example.ysoft.dataAccess.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectService projectService;

    @Mock
    private MapperService mapperService; // MapperService arayüzünü mockluyoruz

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void test_getAllEmployees() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = Arrays.asList(employee1, employee2);

        EmployeeResponseDto responseDto1 = new EmployeeResponseDto();
        EmployeeResponseDto responseDto2 = new EmployeeResponseDto();
        List<EmployeeResponseDto> expected = Arrays.asList(responseDto1, responseDto2);

        when(employeeRepository.findAll()).thenReturn(employees);
        when(mapperService.toResponsee(employee1)).thenReturn(responseDto1);
        when(mapperService.toResponsee(employee2)).thenReturn(responseDto2);

        List<EmployeeResponseDto> actual = employeeService.getAllEmployees();

        assertEquals(expected, actual);
    }

    @Test
    void test_addEmployee() {
        CreateEmployeeRequestDTO requestDTO = new CreateEmployeeRequestDTO();
        UUID projectId = UUID.randomUUID();
        requestDTO.setProjectId(projectId);

        Project project = new Project();
        Employee employee = new Employee();
        Employee savedEmployee = new Employee();
        CreateEmployeeResponseDTO expected = new CreateEmployeeResponseDTO();

        when(projectService.findById(projectId.toString())).thenReturn(project);
        when(mapperService.toEntity(requestDTO, project)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);
        when(mapperService.toCreateEmployeeResponse(savedEmployee)).thenReturn(expected);

        CreateEmployeeResponseDTO actual = employeeService.addEmployee(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_getById() {
        UUID employeeId = UUID.randomUUID();
        Employee employee = new Employee();
        EmployeeResponseDto expected = new EmployeeResponseDto();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(mapperService.toResponsee(employee)).thenReturn(expected);

        EmployeeResponseDto actual = employeeService.getById(employeeId.toString());

        assertEquals(expected, actual);
    }

    @Test
    void test_updateEmployee() {
        UpdateEmployeeRequestDTO requestDTO = new UpdateEmployeeRequestDTO();
        UUID employeeId = UUID.randomUUID();
        requestDTO.setId(employeeId);

        Employee employee = new Employee();
        UpdateEmployeeResponseDTO expected = new UpdateEmployeeResponseDTO();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(mapperService.toUpdateEmployeeResponse(employee)).thenReturn(expected);

        UpdateEmployeeResponseDTO actual = employeeService.updateEmployee(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_deleteEmployee() {
        UUID employeeId = UUID.randomUUID();

        employeeService.deleteEmployee(employeeId.toString());

        verify(employeeRepository).deleteById(employeeId);
    }

    @Test
    void test_getFindEmployeeFullNameById() {
        UUID employeeId = UUID.randomUUID();
        String expected = "selen sari";

        when(employeeRepository.findEmployeeFullNameById(employeeId)).thenReturn(expected);

        String actual = employeeService.getFindEmployeeFullNameById(employeeId.toString());

        assertEquals(expected, actual);
    }
}