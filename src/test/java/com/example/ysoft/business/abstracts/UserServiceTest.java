package com.example.ysoft.business.abstracts;

import com.example.ysoft.api.controller.UserController;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
    }

    @Test
    void test_addUser() {
        // Given
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO("selensari", "password123");

        UUID userId = UUID.randomUUID();
        CreateUserResponseDTO expected = new CreateUserResponseDTO(userId, "selensari", "password123");

        when(userService.addUser(requestDTO)).thenReturn(expected);

        // When
        CreateUserResponseDTO actual = userService.addUser(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_getAllUsers() {
        // Given
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(UUID.randomUUID().toString());

        List<UserResponseDto> expected = List.of(userResponseDto);

        when(userService.getAllUsers()).thenReturn(expected);

        // When
        List<UserResponseDto> actual = userService.getAllUsers();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_getById() {
        // Given
        String userId = UUID.randomUUID().toString();
        UserResponseDto expected = new UserResponseDto();

        when(userService.getById(userId)).thenReturn(expected);

        // When
        UserResponseDto actual = userService.getById(userId);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void test_updateUser() {
        // Given
        UUID userId = UUID.randomUUID();
        UpdateUserRequestDTO requestDTO = new UpdateUserRequestDTO(userId, "selensari", "password123");

        UpdateUserResponseDTO expected = new UpdateUserResponseDTO(userId, "selensari", "password123");

        when(userService.updateUser(requestDTO)).thenReturn(expected);

        // When
        UpdateUserResponseDTO actual = userService.updateUser(requestDTO);

        // Then
        assertEquals(expected, actual);
    }


    @Test
    void test_deleteUser() {
        // Given
        String userId = UUID.randomUUID().toString();

        // When
        userService.deleteUser(userId);

        // Then
        verify(userService, times(1)).deleteUser(userId);
    }
}
