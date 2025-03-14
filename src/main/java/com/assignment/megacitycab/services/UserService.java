package com.assignment.megacitycab.services;

import com.assignment.megacitycab.dtos.UserDTO;
import com.assignment.megacitycab.models.User;
import com.assignment.megacitycab.repositories.CustomerRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

    // Create or Update User
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Create or Update User with Encrypted Password
    public User saveUser(UserDTO userDTO) {
        //check for user
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if(existingUser.isPresent()){
            return existingUser.get();
        }
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
    public long getUserCount() {
        return userRepository.countByDeleteStatusFalse(); // Return the count of active users
    }
    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String id) {
        return userRepository.findByEmail(id);
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

        if (userOptional.isEmpty()) {
            userOptional = customerRepository.findByEmail(email); // Fallback check in CustomerRepo
        }

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("User password "+user.getPassword());
            // Check password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user); // Login successful
            }
        }

        return Optional.empty(); // Login failed
    }

}