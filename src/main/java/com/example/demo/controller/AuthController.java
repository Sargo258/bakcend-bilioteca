package com.example.demo.controller;

import com.example.demo.models.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Codificar la contraseña
        user.setRole(user.getRole()); // Establecer el rol
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        // Implementa la lógica de inicio de sesión aquí si es necesario, por ahora, solo devuelve un mensaje de éxito
        return ResponseEntity.ok("Login successful");
    }
}