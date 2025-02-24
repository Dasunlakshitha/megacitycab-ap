/*
package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.model.AppUser;
import com.assignment.megacitycab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users") // URL to access the user list
    public String listUsers(Model model) { // Add Model parameter
        List<AppUser> users = userService.getAllUsers(); // Get all users

        model.addAttribute("users", users); // Add users to the model
        return "user-list"; // Return the name of your JSP view (user-list.jsp)
    }

}*/
