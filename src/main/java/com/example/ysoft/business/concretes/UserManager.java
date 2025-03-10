package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.UserRequestDto;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.dataAccess.UserRepository;
import com.example.ysoft.entities.User;
import com.example.ysoft.library.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = toEntity(userRequestDto);
        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDto getById(String id) {
        UUID userId = UUID.fromString(id);  // String'den UUID'ye dönüştürme
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));
        return toResponse(user);
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto userRequestDto) {
        UUID userId = UUID.fromString(id);  // String'den UUID'ye dönüştürme
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Bulunamadı"));
        user.setNickName(userRequestDto.getNickName());
        user.setPassword(userRequestDto.getPassword());
        return toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String id) {
        UUID userId = UUID.fromString(id);  // String'den UUID'ye dönüştürme
        userRepository.deleteById(userId);
    }

    private UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId().toString())  // UUID'yi String'e dönüştürmek
                .password(user.getPassword())
                .nickName(user.getNickName())
                .build();
    }

    private User toEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .nickName(userRequestDto.getNickName())
                .password(userRequestDto.getPassword())
                .build();
    }
}
