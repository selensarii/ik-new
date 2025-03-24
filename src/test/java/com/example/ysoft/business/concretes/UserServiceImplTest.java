package com.example.ysoft.business.concretes;

import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import com.example.ysoft.core.mapper.MapperService;
import com.example.ysoft.dataAccess.UserRepository;
import com.example.ysoft.entities.User;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MapperService mapperService; // MapperService arayüzünü mockluyoruz

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void test_getAllUsers() {
        User user1 = new User("nick1", "pass1");
        user1.setId(UUID.randomUUID());
        User user2 = new User("nick2", "pass2");
        user2.setId(UUID.randomUUID());
        List<User> users = Arrays.asList(user1, user2);

        UserResponseDto response1 = new UserResponseDto(user1.getId().toString(), user1.getNickName(), user1.getPassword());
        UserResponseDto response2 = new UserResponseDto(user2.getId().toString(), user2.getNickName(), user2.getPassword());
        List<UserResponseDto> expected = Arrays.asList(response1, response2);

        when(userRepository.findAll()).thenReturn(users);
        when(mapperService.toResponsed(user1)).thenReturn(response1);
        when(mapperService.toResponsed(user2)).thenReturn(response2);

        List<UserResponseDto> actual = userService.getAllUsers();

        assertEquals(expected, actual);
    }

    @Test
    void test_addUser() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("nick1", "pass1");
        User user = new User("nick1", "pass1");
        UUID uuid = UUID.randomUUID();
        CreateUserResponseDTO createUserResponseDTO = new CreateUserResponseDTO(uuid, "nick1", "pass1");

        when(mapperService.toUserEntity(createUserRequestDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(mapperService.toUserResponse(user)).thenReturn(createUserResponseDTO);

        CreateUserResponseDTO actual = userService.addUser(createUserRequestDTO);
        assertEquals(createUserResponseDTO.getNickName(), actual.getNickName());
    }

    @Test
    void test_getById() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User("nick1", "pass1");
        user.setId(userId);
        UserResponseDto expected = new UserResponseDto(userId.toString(), "nick1", "pass1");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mapperService.toResponsed(user)).thenReturn(expected);

        UserResponseDto actual = userService.getById(userId.toString());

        assertEquals(expected, actual);
    }

    @Test
    void test_updateUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UpdateUserRequestDTO requestDTO = new UpdateUserRequestDTO(userId, "updatedNick", "updatedPass");
        User existingUser = new User("oldNick", "oldPass");
        existingUser.setId(userId);
        User updatedUser = new User("updatedNick", "updatedPass");
        updatedUser.setId(userId);

        UpdateUserResponseDTO expected = new UpdateUserResponseDTO(userId, "updatedNick", "updatedPass");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(mapperService.toUpdateUserResponse(updatedUser)).thenReturn(expected);

        UpdateUserResponseDTO actual = userService.updateUser(requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void test_deleteUser() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        userService.deleteUser(userId.toString());

        // Assert
        verify(userRepository).deleteById(userId);
    }
}