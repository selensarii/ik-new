package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.EmployeeService;
import com.example.ysoft.business.dtos.requests.EmployeeRequestDto;
import com.example.ysoft.business.dtos.requests.employee.CreateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.requests.employee.UpdateEmployeeRequestDTO;
import com.example.ysoft.business.dtos.responses.employee.CreateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.employee.UpdateEmployeeResponseDTO;
import com.example.ysoft.business.dtos.responses.EmployeeResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {


    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach //mockları başlatmam gerekti ilk olarak
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAllEmployees() throws Exception {
        // Given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setFullName("John Doe");
        EmployeeResponseDto employeeResponse = new EmployeeResponseDto();
        employeeResponse.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        employeeResponse.setFullName("John Doe");

        List<EmployeeResponseDto> expected =List.of(employeeResponse);

        when(employeeService.getAllEmployees()).thenReturn(List.of(employeeResponse));

        List<EmployeeResponseDto> actual=employeeController.getAllEmployees();
        assertEquals(expected, actual);
    }


    @Test
    void test_addEmployee() {
        // Given
        CreateEmployeeRequestDTO requestDTO = new CreateEmployeeRequestDTO();
        requestDTO.setFullName("selen sari");

        CreateEmployeeResponseDTO expected = new CreateEmployeeResponseDTO();
        expected.setFullName("selen sari");
        when(employeeService.addEmployee(requestDTO)).thenReturn(expected);

        // Act
        CreateEmployeeResponseDTO actual = employeeController.addEmployee(requestDTO);

        // Debug için ekleyelim:
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);

        assertNotNull(actual, "Controller metodu null döndürmemeli!");
        assertEquals(expected, actual);
    }

    @Test
    void test_updateEmployee() throws Exception {
        // Given
        UpdateEmployeeRequestDTO requestDTO = new UpdateEmployeeRequestDTO();
        requestDTO.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        requestDTO.setFullName("Jane Doe");

        UpdateEmployeeResponseDTO expected = new UpdateEmployeeResponseDTO();
        expected.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        expected.setFullName("Jane Doe");

        when(employeeService.updateEmployee(requestDTO)).thenReturn(expected);

        UpdateEmployeeResponseDTO actual = employeeController.updateEmployee(requestDTO);

        // Then
        assertEquals(expected, actual);
    }


    @Test
    void test_deleteEmployee() throws Exception {
        // Given
        String employeeId = "1";
        Mockito.doNothing().when(employeeService).deleteEmployee(employeeId);

        // When
        mockMvc.perform(delete("/employees/v1/employeeId/{employeeId}", employeeId))
                //then
                .andExpect(status().isOk())
                .andExpect(content().string("calisan basariyla silindi: " + employeeId));
    }

    @Test
    void test_getEmployeeFullName() throws Exception {
        // Given
        String employeeId = "1";
        String fullName = "John Doe";

        when(employeeService.getFindEmployeeFullNameById(employeeId)).thenReturn(fullName);

        // When & Then
        mockMvc.perform(get("/employees/v1/fullName/employeeId/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().string(fullName));
    }
}
