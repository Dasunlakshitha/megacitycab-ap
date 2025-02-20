package com.assignment.megacitycab.controllers;


import com.assignment.megacitycab.model.AppUser;
import com.assignment.megacitycab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/api/register", headers = "application/json")
    public AppUser registerUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
