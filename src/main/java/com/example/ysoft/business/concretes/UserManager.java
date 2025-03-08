package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.UserRequest;
import com.example.ysoft.business.dtos.responses.UserResponse;
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
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = toEntity(userRequest);
        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getById(String id) {
        UUID userId = UUID.fromString(id);  // String'den UUID'ye dönüştürme
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));
        return toResponse(user);
    }

    @Override
    public UserResponse updateUser(String id, UserRequest userRequest) {
        UUID userId = UUID.fromString(id);  // String'den UUID'ye dönüştürme
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Bulunamadı"));
        user.setNickName(userRequest.getNickName());
        user.setPassword(userRequest.getPassword());
        return toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String id) {
        UUID userId = UUID.fromString(id);  // String'den UUID'ye dönüştürme
        userRepository.deleteById(userId);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId().toString())  // UUID'yi String'e dönüştürmek
                .password(user.getPassword())
                .nickName(user.getNickName())
                .build();
    }

    private User toEntity(UserRequest userRequest) {
        return User.builder()
                .nickName(userRequest.getNickName())
                .password(userRequest.getPassword())
                .build();
    }
}
