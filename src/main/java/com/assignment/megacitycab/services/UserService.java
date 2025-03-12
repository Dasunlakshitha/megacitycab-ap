package com.assignment.megacitycab.services;

import com.assignment.megacitycab.dtos.UserDTO;
import com.assignment.megacitycab.models.User;
import com.assignment.megacitycab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or Update User
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Create or Update User with Encrypted Password
    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
        return userRepository.save(user);
    }
    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update fields from DTO
            user.setName(userDTO.getName());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());

            // Encrypt password if it is provided in the DTO
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findByDeleteStatusFalse();
    }

    // Delete User by ID
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setDeleteStatus(true);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Login
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setId(userOptional.get().getId());
            // Check if the provided password matches the encrypted password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return userOptional; // Login successful
            }
        }
        return Optional.empty(); // Login failed
    }
}