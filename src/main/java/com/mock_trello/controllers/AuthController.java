package com.mock_trello.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mock_trello.models.User;
import com.mock_trello.services.JwtService;
import com.mock_trello.services.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    // Constructor for dependency injection
    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        
        // Perform authentication check
        if (existingUser != null && userService.checkPassword(user.getPassword(), existingUser.getPassword())) {
            // Generate JWT token
            String token = jwtService.generateToken(existingUser.getUsername());
            return ResponseEntity.ok(token); // Return the generated token
        }

        // If authentication fails
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    
    // GET method to retrieve user details
    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user); // Return user details
        }
        return ResponseEntity.status(404).body(null); // User not found
    }

    // Optional: Check if the user is authenticated (or any role-specific check)
    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth(@RequestHeader("Authorization") String token) {
        // Logic to verify the token and return authentication status
        if (jwtService.validateToken(token.replace("Bearer ", ""), "expectedUsername")) {
            return ResponseEntity.ok("User is authenticated"); // Or provide user details
        }
        return ResponseEntity.status(401).body("User is not authenticated");
    }
    
}
