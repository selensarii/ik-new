package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.UserRequestDto;
import com.example.ysoft.business.dtos.responses.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto addUser(UserRequestDto userRequestDto);
    UserResponseDto getById(String id);
    UserResponseDto updateUser(String id, UserRequestDto userRequestDto);
    void deleteUser(String id);
}