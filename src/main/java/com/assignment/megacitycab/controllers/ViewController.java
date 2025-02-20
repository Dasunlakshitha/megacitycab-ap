package com.assignment.megacitycab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String showIndexPage(Model model) {
        return "home";  // This maps to /WEB-INF/views/index.jsp
    }

}
