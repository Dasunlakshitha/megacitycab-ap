package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.dtos.BookingDTO;
import com.assignment.megacitycab.models.Booking;
import com.assignment.megacitycab.services.BookingService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create or Update Booking
    @PostMapping
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.saveBooking(bookingDTO);
    }

    // Get Booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get All Bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Delete Booking by ID
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingService.updateBooking(id, bookingDTO);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response) throws IOException {
        // Fetch bookings from the service
        List<Booking> bookings = bookingService.getAllBookings();

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"bookings.pdf\"");

        // Create PDF document
        try  {
            var document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Add content to the PDF
            document.add(new Paragraph("Booking Report"));
            document.add(new Paragraph(" ")); // Add a blank line

            for (Booking booking : bookings) {
                document.add(new Paragraph("Booking ID: " + booking.getId()));
                document.add(new Paragraph("Customer: " + booking.getCustomer().getName()));
                document.add(new Paragraph("Driver: " + booking.getDriver().getName()));
                document.add(new Paragraph("Vehicle: " + booking.getVehicle().getModel()));
                document.add(new Paragraph("Pickup: " + booking.getPickupLocation()));
                document.add(new Paragraph("Dropoff: " + booking.getDropoffLocation()));
                document.add(new Paragraph("Fare: " + booking.getFare()));
                document.add(new Paragraph("Status: " + booking.getStatus()));
                document.add(new Paragraph(" ")); // Add a blank line between bookings
            }

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Failed to generate PDF", e);
        }
    }
}