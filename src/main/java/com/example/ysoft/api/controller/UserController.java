package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.UserRequestDto;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.addUser(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable(value = "id") String id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable(name = "id") String id, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") String id){
        userService.deleteUser(id);
        String message = "Kullanıcı başarıyla silindi."+ id;
        return ResponseEntity.ok(message);
    }
}