package com.mock_trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mock_trello.exceptions.UserAlreadyExistsException;
import com.mock_trello.models.AccountUser;
import com.mock_trello.services.AccountUserService;
import com.mock_trello.utils.UUIDConverter;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users") // Base URL for this controller
public class AccountUserController {

    private final AccountUserService accountUserService;

    @Autowired
    public AccountUserController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AccountUser accountUser) {
        try {
            // Check if the user already exists
            if (accountUserService.userExists(accountUser.getEmail())) {
                throw new UserAlreadyExistsException("User with email " + accountUser.getEmail() + " already exists.");
            }

            AccountUser savedUser = null;
            try {
                savedUser = accountUserService.saveUser(accountUser);
            } catch (Exception e) {
                e.printStackTrace(); // Still handles the exception
            }
            return ResponseEntity.ok(savedUser);

        } catch (UserAlreadyExistsException e) {
            // Return error response if user exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Retrieve a User by ID
    @GetMapping("/{uuidString}")
    public ResponseEntity<AccountUser> getUserById(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        Optional<AccountUser> user = accountUserService.findUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all Users
    @GetMapping
    public ResponseEntity<List<AccountUser>> getAllUsers() {
        List<AccountUser> users = accountUserService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // Update a User by ID
    @PutMapping("/{uuidString}")
    public ResponseEntity<AccountUser> updateUser(
            @PathVariable String uuidString,
            @RequestBody AccountUser updatedUser) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            AccountUser user = accountUserService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a User by ID
    @DeleteMapping("/{uuidString}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uuidString) {
        try {
        	byte[] id = UUIDConverter.fromString(uuidString);
            accountUserService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}