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

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable(value = "id") String id) {
        return userService.getById(id);
    }

    @PutMapping("/v1/{userId}")
    public UpdateUserResponseDTO updateUser(@PathVariable UUID userId, @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        updateUserRequestDTO.setId(userId);
        return userService.updateUser(updateUserRequestDTO);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") String id){
        userService.deleteUser(id);
        String message = "Kullanıcı başarıyla silindi."+ id;
        return ResponseEntity.ok(message);
    }
}