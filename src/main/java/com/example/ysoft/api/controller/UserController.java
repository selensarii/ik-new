package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.UserRequestDto;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public CreateUserResponseDTO addUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return userService.addUser(createUserRequestDTO);
    }

    @GetMapping("/v1/userId/{userId}")
    public UserResponseDto getById(@PathVariable(value = "userId") String userId) {
        return userService.getById(userId);
    }

    @PutMapping("/v1/userId")
    public UpdateUserResponseDTO updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return userService.updateUser(updateUserRequestDTO);
    }


    @DeleteMapping("/v1/userId/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") String userId){
        userService.deleteUser(userId);
        String message = "Kullanıcı başarıyla silindi."+ userId;
        return ResponseEntity.ok(message);
    }
}