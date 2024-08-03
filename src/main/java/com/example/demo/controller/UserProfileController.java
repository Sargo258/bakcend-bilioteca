package com.example.demo.controller;

import com.example.demo.dto.UserProfileDTO;
import com.example.demo.models.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO) {
        Users updatedUser = userService.updateUserProfile(id, userProfileDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
