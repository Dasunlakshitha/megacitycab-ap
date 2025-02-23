package com.assignment.megacitycab.controllers;


import com.assignment.megacitycab.model.AppUser;
import com.assignment.megacitycab.repository.UserRepository;
import com.assignment.megacitycab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/api/register", headers = "application/json")
    public AppUser registerUser(@RequestBody AppUser user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userService.registerUser(user);
        } catch (DataIntegrityViolationException e) {  // Handle unique constraint violation
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists"); // Or return a custom error response
        }
    }

    @PostMapping("/api/test")
    public String testEndpoint(@RequestBody String data) {
        System.out.println("Received data: " + data);
        return "Test successful";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This maps to /WEB-INF/views/login.jsp
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // This maps to /WEB-INF/views/login.jsp
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";  // This maps to /WEB-INF/views/login.jsp
    }
}
