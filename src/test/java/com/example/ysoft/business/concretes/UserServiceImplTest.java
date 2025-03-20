package com.example.ysoft.business.concretes;

import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import com.example.ysoft.dataAccess.UserRepository;
import com.example.ysoft.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private CreateUserRequestDTO createUserRequestDTO;
    private UpdateUserRequestDTO updateUserRequestDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User("testNick", "testPassword");
        user.setId(UUID.randomUUID());

        createUserRequestDTO = new CreateUserRequestDTO("testNick", "testPassword");
        updateUserRequestDTO = new UpdateUserRequestDTO(user.getId(), "updatedNick", "updatedPassword");
    }

    @Test
    public void testAddUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        CreateUserResponseDTO actual = userService.addUser(createUserRequestDTO);

        assertNotNull(actual);
        assertEquals(user.getId(), actual.getId());
        assertEquals(user.getNickName(), actual.getNickName());
        assertEquals(user.getPassword(), actual.getPassword());
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDto> actual = userService.getAllUsers();

        assertNotNull(actual);
        assertEquals(1, actual.size()); //listenin büyüklüğünün 1 omasını bekliyoruz yani 1 kullanıcı
        assertEquals(user.getNickName(), actual.get(0).getNickName()); //buradaki 0 listenin ilk elemanı
    }

    @Test
    public void test_getById() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        UserResponseDto actual = userService.getById(user.getId().toString());

        assertNotNull(actual);
        assertEquals(user.getNickName(), actual.getNickName());
        assertEquals(user.getPassword(), actual.getPassword());
    }

    @Test
    public void test_updateUser() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UpdateUserResponseDTO actual = userService.updateUser(updateUserRequestDTO);

        assertEquals(user.getId(), actual.getId());
        assertEquals("updatedNick", actual.getNickName()); //burayı sor
        assertEquals("updatedPassword", actual.getPassword());
    }

    @Test
    public void testDeleteUser() {

        userService.deleteUser(user.getId().toString());

        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }
}
