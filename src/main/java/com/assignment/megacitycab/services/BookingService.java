package com.assignment.megacitycab.services;

import com.assignment.megacitycab.dtos.BookingDTO;
import com.assignment.megacitycab.models.Booking;
import com.assignment.megacitycab.models.Customer;
import com.assignment.megacitycab.models.Driver;
import com.assignment.megacitycab.models.Vehicle;
import com.assignment.megacitycab.repositories.BookingRepository;
import com.assignment.megacitycab.repositories.CustomerRepository;
import com.assignment.megacitycab.repositories.DriverRepository;
import com.assignment.megacitycab.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    // Create or Update Booking
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Booking saveBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        System.out.println(bookingDTO.getCustomerId());
        // Find and set related entities
        Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        booking.setCustomer(customer);

        // Fetch driver, fallback to first available if not found
        Driver driver =
                bookingDTO.getDriverId() == null ?
                        driverRepository.findAll().stream().findFirst()
                                .orElseThrow(() -> new RuntimeException("No available drivers"))
        :

                driverRepository.findById(bookingDTO.getDriverId())
                .orElseGet(() -> driverRepository.findAll().stream().findFirst()
                        .orElseThrow(() -> new RuntimeException("No available drivers")));

// Set the driver
        booking.setDriver(driver);

// Fetch vehicle, fallback to first available if not found
        Vehicle vehicle =

                bookingDTO.getVehicleId() == null ?
                        vehicleRepository.findAll().stream().findFirst()
                                .orElseThrow(() -> new RuntimeException("No available vehicles"))
        :
                vehicleRepository.findById(bookingDTO.getVehicleId())
                .orElseGet(() -> vehicleRepository.findAll().stream().findFirst()
                        .orElseThrow(() -> new RuntimeException("No available vehicles")));

// Set the vehicle
        booking.setVehicle(vehicle);


        // Set other fields
        booking.setPickupLocation(bookingDTO.getPickupLocation());
        booking.setDropoffLocation(bookingDTO.getDropoffLocation());
        booking.setFare(bookingDTO.getFare());
        booking.setStatus(bookingDTO.getStatus());
        booking.setBookingTime(bookingDTO.getBookingTime() != null ?
                bookingDTO.getBookingTime() : LocalDateTime.now());

        return bookingRepository.save(booking);
    }
    // Get Booking by ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findByDeleteStatusFalse();
    }

    // Delete Booking by ID
    public void deleteBooking(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setDeleteStatus(true);
            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    public Booking updateBooking(Long id, BookingDTO bookingDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            // Find and set related entities if they're provided in the DTO
            if (bookingDTO.getCustomerId() != null) {
                Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
                booking.setCustomer(customer);
            }

            if (bookingDTO.getDriverId() != null) {
                Driver driver = driverRepository.findById(bookingDTO.getDriverId())
                        .orElseThrow(() -> new RuntimeException("Driver not found"));
                booking.setDriver(driver);
            }

            if (bookingDTO.getVehicleId() != null) {
                Vehicle vehicle = vehicleRepository.findById(bookingDTO.getVehicleId())
                        .orElseThrow(() -> new RuntimeException("Vehicle not found"));
                booking.setVehicle(vehicle);
            }

            // Update other fields if they're not null
            if (bookingDTO.getPickupLocation() != null) {
                booking.setPickupLocation(bookingDTO.getPickupLocation());
            }

            if (bookingDTO.getDropoffLocation() != null) {
                booking.setDropoffLocation(bookingDTO.getDropoffLocation());
            }

            if (bookingDTO.getFare() > 0) {
                booking.setFare(bookingDTO.getFare());
            }

            if (bookingDTO.getStatus() != null) {
                booking.setStatus(bookingDTO.getStatus());
            }

            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }
}