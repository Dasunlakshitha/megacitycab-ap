package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.dtos.LoginDTO;
import com.assignment.megacitycab.dtos.UserDTO;
import com.assignment.megacitycab.models.User;
import com.assignment.megacitycab.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create or Update User
    @PostMapping
    public User saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Delete User by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Login
    @PostMapping("/login" )
    public ResponseEntity<User> login(@RequestBody LoginDTO logins) {
        Optional<User> user = userService.login(logins.getEmail(), logins.getPassword());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        try {
            User updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        long count = userService.getUserCount();
        return ResponseEntity.ok(count);
    }
}