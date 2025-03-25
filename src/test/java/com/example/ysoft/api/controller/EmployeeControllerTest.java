package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.core.utils.MessageConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;
    

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void test_getAllEmployees() {
        EmployeeResponseDto employeeResponseDto1 = new EmployeeResponseDto();
        employeeResponseDto1.setId(UUID.randomUUID());
        employeeResponseDto1.setFullName("Selen Sari");
        employeeResponseDto1.setPosition("Developer");

        EmployeeResponseDto employeeResponseDto2 = new EmployeeResponseDto();
        employeeResponseDto2.setId(UUID.randomUUID());
        employeeResponseDto2.setFullName("Filiz Sari");
        employeeResponseDto2.setPosition("Tester");

        List<EmployeeResponseDto> expected = Arrays.asList(employeeResponseDto1, employeeResponseDto2);

        when(employeeService.getAllEmployees()).thenReturn(expected);

        List<EmployeeResponseDto> actual = employeeController.getAllEmployees();

        assertEquals(expected, actual);
    }

    @Test
    void test_addEmployee() {
        CreateEmployeeRequestDTO createEmployeeRequestDTO = new CreateEmployeeRequestDTO();
        createEmployeeRequestDTO.setFullName("selen sari");
        createEmployeeRequestDTO.setPosition("developer");

        UUID uuid = UUID.randomUUID();
        CreateEmployeeResponseDTO expected = new CreateEmployeeResponseDTO();
        expected.setId(uuid);
        expected.setFullName("selen sari");
        expected.setPosition("developer");

        when(employeeService.addEmployee(createEmployeeRequestDTO)).thenReturn(expected);

        CreateEmployeeResponseDTO actual = employeeController.addEmployee(createEmployeeRequestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_updateEmployee() throws JsonProcessingException {
        UpdateEmployeeRequestDTO updateEmployeeRequestDTO = new UpdateEmployeeRequestDTO();
        updateEmployeeRequestDTO.setId(UUID.randomUUID());
        updateEmployeeRequestDTO.setFullName("selen sari");
        updateEmployeeRequestDTO.setPosition("tester");

        UpdateEmployeeResponseDTO expected = new UpdateEmployeeResponseDTO();
        expected.setId(updateEmployeeRequestDTO.getId());
        expected.setFullName("selen sari");
        expected.setPosition("tester");

        when(employeeService.updateEmployee(updateEmployeeRequestDTO)).thenReturn(expected);

        UpdateEmployeeResponseDTO actual = employeeController.updateEmployee(updateEmployeeRequestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_deleteEmployee() {
        String employeeId = UUID.randomUUID().toString();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(MessageConstant.EMPLOYEE_DELETED_SUCCESSFULLY + employeeId, HttpStatus.OK);

        ResponseEntity<String> actualResponse = employeeController.deleteEmployee(employeeId);

        assertEquals(expectedResponse, actualResponse);
        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }

    @Test
    void test_getEmployeeFullName() {
        String employeeId = UUID.randomUUID().toString();
        String expected = "John Doe";

        when(employeeService.getFindEmployeeFullNameById(employeeId)).thenReturn(expected);

        String actual = employeeController.getEmployeeFullName(employeeId);

        assertEquals(expected, actual);
    }
}