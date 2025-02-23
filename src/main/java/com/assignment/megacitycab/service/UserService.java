package com.assignment.megacitycab.service;


import com.assignment.megacitycab.model.AppUser;
import com.assignment.megacitycab.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; // For password hashing

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        checkDatabaseConnection();
    }

    public AppUser registerUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        user.setRole("ROLE_" + user.getRole());
        return userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll(); // Use JpaRepository's findAll()
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void checkDatabaseConnection() {
        try {
            String sql = "SELECT 1"; // A very simple query
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
            if (result != null && result == 1) {
                System.out.println("Database connection is successful!");
            } else {
                System.out.println("Database connection test failed.");
            }
        } catch (Exception e) {
            System.err.println("Database connection test failed: " + e.getMessage());
        }
    }

}
