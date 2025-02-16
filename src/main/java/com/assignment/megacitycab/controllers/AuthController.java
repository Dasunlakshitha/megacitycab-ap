package com.assignment.megacitycab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public String showIndexPage() {
        return "index";  // This maps to /WEB-INF/views/index.jsp
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // This maps to /WEB-INF/views/register.jsp
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This maps to /WEB-INF/views/login.jsp
    }
}
