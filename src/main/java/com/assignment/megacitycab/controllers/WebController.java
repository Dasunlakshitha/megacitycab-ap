package com.assignment.megacitycab.controllers;
import com.assignment.megacitycab.models.*;
import com.assignment.megacitycab.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class WebController {
    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/login")
    public String login() {
        return "login"; // Returns login.jsp
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Returns register.jsp
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Returns register.jsp
    }
    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // Returns users.jsp
    }



    @GetMapping("/create-user")
    public String createUser(Model model) {

        return "create-user"; // Returns users.jsp
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "edit-user"; // Returns edit-user.jsp
        } else {
            return "redirect:/users"; // Redirect if user not found
        }
    }
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return "redirect:/users"; // Redirect to vehicles page after deletion
    }
    // Vehicles Page
    @GetMapping("/vehicles")
    public String vehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicles"; // Returns vehicles.jsp
    }

    // Create Vehicle Page
    @GetMapping("/create-vehicle")
    public String createVehicle() {
        return "create-vehicle"; // Returns create-vehicle.jsp
    }

    // Edit Vehicle Page
    @GetMapping("/edit-vehicle/{id}")
    public String editVehicle(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            return "edit-vehicle"; // Returns edit-vehicle.jsp
        } else {
            return "redirect:/vehicles"; // Redirect if vehicle not found
        }
    }

    // Delete Vehicle
    @GetMapping("/delete-vehicle/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles"; // Redirect to vehicles page after deletion
    }

    @GetMapping("/drivers")
    public String drivers(Model model) {
        List<Driver> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "drivers"; // Returns drivers.jsp
    }

    // Create Driver Page
    @GetMapping("/create-driver")
    public String createDriver() {
        return "create-driver"; // Returns create-driver.jsp
    }

    // Edit Driver Page
    @GetMapping("/edit-driver/{id}")
    public String editDriver(@PathVariable Long id, Model model) {
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            model.addAttribute("driver", driver.get());
            return "edit-driver"; // Returns edit-driver.jsp
        } else {
            return "redirect:/drivers"; // Redirect if driver not found
        }
    }

    // Delete Driver
    @GetMapping("/delete-driver/{id}")
    public String deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return "redirect:/drivers"; // Redirect to drivers page after deletion
    }

    @GetMapping("/bookings")
    public String bookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings"; // Returns bookings.jsp
    }
    @GetMapping("/user-bookings")
    public String userBookings(@RequestParam("email") String email, Model model) {

        Optional<User> existingUser = userService.getUserByEmail(email);

        if(existingUser.isPresent()){
            User user = existingUser.get();
            model.addAttribute("user", user);
            return "user-bookings"; // Returns bookings.jsp
        }else{
            return "/";
        }
    }

    @GetMapping("/my-bookings")
    public String myBookings(@RequestParam("email") String email, Model model) {

        Optional<User> existingUser = userService.getUserByEmail(email);
        List<Booking> bookings = bookingService.getAllBookings();
        List<Booking> filteredBookings = existingUser
                .map(user -> bookings.stream()
                        .filter(booking ->
                                (booking.getDriver() != null && booking.getDriver().getId().equals(user.getId())) ||
                                        (booking.getCustomer() != null && booking.getCustomer().getId().equals(user.getId())))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        if(existingUser.isPresent()){
            User user = existingUser.get();
            model.addAttribute("user", user);
            model.addAttribute("bookings", filteredBookings);
            return "my-bookings"; // Returns bookings.jsp
        }else{
            return "/";
        }
    }

    // Create Booking Page
    @GetMapping("/create-booking")
    public String createBooking(Model model) {
        List<Driver> drivers = driverService.getAllDrivers();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("drivers", drivers);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("customers", customers);
        return "create-booking"; // Returns create-booking.jsp
    }

    // Edit Booking Page
    @GetMapping("/edit-booking/{id}")
    public String editBooking(@PathVariable Long id, Model model) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            List<Driver> drivers = driverService.getAllDrivers();
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            model.addAttribute("booking", booking.get());
            model.addAttribute("drivers", drivers);
            model.addAttribute("vehicles", vehicles);
            return "edit-booking"; // Returns edit-booking.jsp
        } else {
            return "redirect:/bookings"; // Redirect if booking not found
        }
    }

    // Delete Booking
    @GetMapping("/delete-booking/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings"; // Redirect to bookings page after deletion
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers"; // Returns customers.jsp
    }

    // Create Customer Page
    @GetMapping("/create-customer")
    public String createCustomer() {
        return "create-customer"; // Returns create-customer.jsp
    }

    // Edit Customer Page
    @GetMapping("/edit-customer/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "edit-customer"; // Returns edit-customer.jsp
        } else {
            return "redirect:/customers"; // Redirect if customer not found
        }
    }

    // Delete Customer
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers"; // Redirect to customers page after deletion
    }
    @GetMapping("/payments")
    public String payments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payments"; // Returns payments.jsp
    }

    // Create Payment Page
    @GetMapping("/create-payment")
    public String createPayment(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "create-payment"; // Returns create-payment.jsp
    }

    // Edit Payment Page
    @GetMapping("/edit-payment/{id}")
    public String editPayment(@PathVariable Long id, Model model) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isPresent()) {
            List<Booking> bookings = bookingService.getAllBookings();
            model.addAttribute("payment", payment.get());
            model.addAttribute("bookings", bookings);
            return "edit-payment"; // Returns edit-payment.jsp
        } else {
            return "redirect:/payments"; // Redirect if payment not found
        }
    }

    // Delete Payment
    @GetMapping("/delete-payment/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments"; // Redirect to payments page after deletion
    }


}