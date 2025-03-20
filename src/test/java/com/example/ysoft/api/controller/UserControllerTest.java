package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach //mockları başlatmam gerekti ilk olarak
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAll() {
        // Given
        UserResponseDto user = new UserResponseDto("1", "selensari", "password123");
        List<UserResponseDto> expected = List.of(user);

        // When
        when(userService.getAllUsers()).thenReturn(List.of(user));

        // Then
        List<UserResponseDto> actual = userController.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void test_addUser() throws Exception {
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO("selensari", "password123");

        CreateUserResponseDTO expected = new CreateUserResponseDTO(UUID.randomUUID(), "selensari", "password123");

        when(userService.addUser(requestDTO)).thenReturn(expected);
        CreateUserResponseDTO actual = userController.addUser(requestDTO);
        assertEquals(expected, actual);

    }

    @Test
    void test_getById() throws Exception {
        String userId = UUID.randomUUID().toString();
        UserResponseDto expected = new UserResponseDto(userId, "selensari", "password123");

        when(userService.getById("userId")).thenReturn(expected);
        UserResponseDto actual = userController.getById("userId");
        assertEquals(expected, actual);

    }

    @Test
    void test_updateUser() throws Exception {
        UUID userId = UUID.randomUUID();

        UpdateUserRequestDTO requestDTO = new UpdateUserRequestDTO(userId, "selensari", "password123");
        UpdateUserResponseDTO expected = new UpdateUserResponseDTO(userId, "JohnDoeUpdated", "newpassword123");


        when(userService.updateUser(requestDTO)).thenReturn(expected);
        UpdateUserResponseDTO actual = userController.updateUser(requestDTO);
        assertEquals(expected, actual);


    }

    @Test
    void test_deleteUser() throws Exception {
        String userId = UUID.randomUUID().toString();
        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/v1/userId/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("kullanici silindi." + userId));

    }
}
