package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.model.Booking;
import com.assignment.megacitycab.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")

    public String showIndexPage(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        System.out.println("Bookings: " + bookings);
        model.addAttribute("bookings", bookings);
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
