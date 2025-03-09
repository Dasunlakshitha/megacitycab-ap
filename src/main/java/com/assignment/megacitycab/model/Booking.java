package com.assignment.megacitycab.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingNumber;
    private String customerName;
    private String customerAddress;
    private String customerNIC;
    private String telephoneNumber;
    private String destination;
    private LocalDateTime bookingTime;
    private double fare;
    private String status; // e.g., PENDING, COMPLETED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
