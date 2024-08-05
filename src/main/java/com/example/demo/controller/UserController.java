package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.models.Users;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Users user) {
        Users registeredUser = userService.registerUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody UserDTO userDto) {
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole() != null && !userDto.getRole().isEmpty() ? userDto.getRole() : "registered");
        Users createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Users updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/profile/{id}")
    public ResponseEntity<Users> updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserProfileDTO userProfileDTO) {
        Users updatedUser = userService.updateUserProfile(id, userProfileDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/admin/update-role/{id}")
    public ResponseEntity<Users> updateUserRole(@PathVariable Long id, @RequestParam String role) {
        Users updatedUser = userService.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUser);
    }
}
