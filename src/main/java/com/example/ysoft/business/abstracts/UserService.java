package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getById(String id);
    void deleteUser(String id);


    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO);
    CreateUserResponseDTO addUser(CreateUserRequestDTO createUserRequestDTO);
}