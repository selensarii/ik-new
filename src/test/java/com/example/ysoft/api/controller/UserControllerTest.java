package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void test_getAll() {
        UserResponseDto userResponseDto1 = new UserResponseDto();
        userResponseDto1.setId(UUID.randomUUID().toString());
        userResponseDto1.setNickName("user1");

        UserResponseDto userResponseDto2 = new UserResponseDto();
        userResponseDto2.setId(UUID.randomUUID().toString());
        userResponseDto2.setNickName("user2");

        List<UserResponseDto> expected = Arrays.asList(userResponseDto1, userResponseDto2);

        when(userService.getAllUsers()).thenReturn(expected);

        List<UserResponseDto> actual = userController.getAll();

        assertEquals(expected, actual);
    }
    @Test
    void test_addUser() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("selensari", "password123");

        UUID uuid = UUID.randomUUID();
        CreateUserResponseDTO expected = new CreateUserResponseDTO(uuid, "selensari", "password123");

        when(userService.addUser(createUserRequestDTO)).thenReturn(expected);

        CreateUserResponseDTO actual = userController.addUser(createUserRequestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_getById() {
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        UserResponseDto expected = new UserResponseDto();
        expected.setId(userId);
        expected.setNickName("selensari");

        when(userService.getById(userId)).thenReturn(expected);

        UserResponseDto actual = userController.getById(userId);

        assertEquals(expected, actual);
    }

    @Test
    void test_updateUser() {
        UUID uuid = UUID.randomUUID();
        UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO(
                uuid,
                "updatedUser",
                "updatedPassword"
        );

        UpdateUserResponseDTO expected = new UpdateUserResponseDTO(
                uuid,
                "updatedUser",
                "updatedPassword"
        );

        when(userService.updateUser(updateUserRequestDTO)).thenReturn(expected);

        UpdateUserResponseDTO actual = userController.updateUser(updateUserRequestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_deleteUser() {
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        String message = "kullanici silindi." + userId;
        ResponseEntity<String> expected = new ResponseEntity<>(message, HttpStatus.OK);

        ResponseEntity<String> actual = userController.deleteUser(userId);

        assertEquals(expected, actual);
        verify(userService, times(1)).deleteUser(userId);
    }
}