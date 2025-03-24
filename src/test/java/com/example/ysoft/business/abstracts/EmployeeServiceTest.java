package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
    }

    @Test
    void test_getAllEmployees() {
        // Given
        UUID employeeId = UUID.randomUUID();

        EmployeeResponseDto employeeResponse = new EmployeeResponseDto();
        employeeResponse.setId(employeeId);
        employeeResponse.setFullName("Selen sari");

        List<EmployeeResponseDto> expected = List.of(employeeResponse);

        when(employeeService.getAllEmployees()).thenReturn(expected);

        // When
        List<EmployeeResponseDto> actual = employeeService.getAllEmployees();

        // Then
        assertEquals(expected, actual);
    }


    @Test
    void test_getById() {
        // Given
        UUID employeeId = UUID.randomUUID();
        EmployeeResponseDto expected = new EmployeeResponseDto();
        expected.setId(employeeId);
        expected.setFullName("Selen sari");

        when(employeeService.getById(employeeId.toString())).thenReturn(expected);

        // When
        EmployeeResponseDto actual = employeeService.getById(employeeId.toString()); 

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_addEmployee() {
        // Given
        CreateEmployeeRequestDTO requestDTO = new CreateEmployeeRequestDTO();
        requestDTO.setFullName("Selen sari");

        CreateEmployeeResponseDTO expected = new CreateEmployeeResponseDTO();
        expected.setFullName("Selen sari");

        when(employeeService.addEmployee(requestDTO)).thenReturn(expected);

        CreateEmployeeResponseDTO actual = employeeService.addEmployee(requestDTO);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_updateEmployee() {
        // Given
        UpdateEmployeeRequestDTO requestDTO = new UpdateEmployeeRequestDTO();
        requestDTO.setFullName("Update Selen sari ");

        UpdateEmployeeResponseDTO expected = new UpdateEmployeeResponseDTO();
        expected.setFullName("Update Selen sari");

        when(employeeService.updateEmployee(requestDTO)).thenReturn(expected);

        UpdateEmployeeResponseDTO actual = employeeService.updateEmployee(requestDTO);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_deleteEmployee() {
        // Given
        String employeeId = UUID.randomUUID().toString();
        doNothing().when(employeeService).deleteEmployee(employeeId);

        // When - Then
        assertDoesNotThrow(() -> employeeService.deleteEmployee(employeeId));
        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }

    @Test
    void test_getFindEmployeeFullNameById() {
        // Given
        String employeeId = UUID.randomUUID().toString();
        String expected = "Selen sari";

        when(employeeService.getFindEmployeeFullNameById(employeeId)).thenReturn(expected);

        // When
        String actual = employeeService.getFindEmployeeFullNameById(employeeId);

        // Then
        assertEquals(expected, actual);
    }
}