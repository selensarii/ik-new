package com.example.ysoft.api.controller;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.UserRequest;
import com.example.ysoft.business.dtos.responses.UserResponse;
import lombok.RequiredArgsConstructor;
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
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable(value = "id") String id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable(name = "id") String id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") String id){
        userService.deleteUser(id);
        String message = "Kullanıcı başarıyla silindi."+ id;
        return ResponseEntity.ok(message);
    }
}