package com.example.ysoft.business.abstracts;

import com.example.ysoft.business.dtos.requests.UserRequest;
import com.example.ysoft.business.dtos.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse addUser(UserRequest userRequest);
    UserResponse getById(String id);
    UserResponse updateUser(String id, UserRequest userRequest);
    void deleteUser(String id);
}