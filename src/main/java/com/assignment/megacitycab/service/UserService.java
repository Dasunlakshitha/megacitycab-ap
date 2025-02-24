package com.assignment.megacitycab.service;/*
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
*/

import com.assignment.megacitycab.model.User;
import com.assignment.megacitycab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public int registerUser(String user_name, String password, String user_role,String token, int code) {
        return userRepository.registerUser(user_name, password, user_role, token, code);
    }// END OF REGISTER USER SERVICE METHOD.


    public int verifyAccount(String token, int code) {
        return userRepository.verifyAccount(token, code);
    }// END OF VERIFY ACCOUNT SERVICE METHOD.


    public List<String> checkTokenAndCode(String token, int code) {
        return userRepository.checkTokenAndCode(token, code);
    }// END OF CHECK TOKEN AND CODE SERVICE METHOD.

    public String doesEmailExist(String email) {
        return userRepository.doesEmailExist(email);
    }// END OF DOES EMAIL EXIST SERVICE METHOD.

    public int isAccountVerified(String email) {
        return userRepository.isAccountVerified(email);
    }// END OF CHECK IF ACCOUNT IS VERIFIED.

    public String getDbPasswordByEmail(String email) {
        return userRepository.getDbPasswordByEmail(email);
    }// END OF GET DB PASSWORD BY EMAIL SERVICE METHOD.


    public User getUserDetailsByEmail(String email) {
        return userRepository.getUserDetailsByEmail(email);
    }// END OF GET USER DETAILS BY EMAIL SERVICE METHOD.

    public int forgotPassword(String token, String code, String password, String Email) {
        return userRepository.forgotPassword(token, code, password);
    }// END OF FORGOT PASSWORD SERVICE METHOD.

    public int changePassword(String token, String code, String password) {
        return userRepository.changePassword(token, code, password);
    }// END OF CHANGE PASSWORD SERVICE METHOD.

    public String getHashedPasswordByTokenAndCode(String token, String code) {
        return userRepository.getHashedPasswordByTokenAndCode(token, code);
    }// END OF GET HASHED PASSWORD BY TOKEN AND CODE SERVICE METHOD.

    public int updatePasswordByEmail(String password, String email) {
        return userRepository.updatePasswordByEmail(password, email);
    }// END OF UPDATE PASSWORD BY EMAIL.

}// END OF USER SERVICE CLASS.