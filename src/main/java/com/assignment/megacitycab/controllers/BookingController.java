package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.dtos.BookingDTO;
import com.assignment.megacitycab.models.Booking;
import com.assignment.megacitycab.services.BookingService;
import com.itextpdf.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

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
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Title with formatting
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Mega City Cab - Booking Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n")); // Line break

            // Table for better formatting
            PdfPTable table = new PdfPTable(7); // 7 columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setWidths(new float[]{10f, 20f, 20f, 20f, 20f, 15f, 15f});

            // Table Header
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            String[] headers = {"ID", "Customer", "Driver", "Vehicle", "Pickup", "Dropoff", "Fare"};

            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
                headerCell.setBackgroundColor(BaseColor.DARK_GRAY);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            // Add booking details to table
            Font cellFont = new Font(Font.FontFamily.HELVETICA, 11);
            for (Booking booking : bookings) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(booking.getId()), cellFont)));
                table.addCell(new PdfPCell(new Phrase(booking.getCustomer().getName(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(booking.getDriver().getName(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(booking.getVehicle().getModel(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(booking.getPickupLocation(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(booking.getDropoffLocation(), cellFont)));
                table.addCell(new PdfPCell(new Phrase("$" + booking.getFare(), cellFont)));
            }

            document.add(table);
            document.add(new Paragraph("\n")); // Line break

            // Footer with timestamp
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
            Paragraph footer = new Paragraph("Generated on: " + new java.util.Date(), footerFont);
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Failed to generate PDF", e);
        }
    }

    @GetMapping("/generate-pdf/{bookingId}")
    public void generatePdfById(@PathVariable Long bookingId, HttpServletResponse response) throws IOException {
        // Fetch booking by ID from the service
        Optional<Booking> bookingOptional = bookingService.getBookingById(bookingId);

        if (bookingOptional.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
            return;
        }

        Booking booking = bookingOptional.get(); // Unwrap the Optional

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"booking_" + bookingId + ".pdf\"");

        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Title (Bold, Centered)
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("Mega City Cab - Booking Bill", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n")); // Blank Line

            // Booking Details Table
            PdfPTable table = new PdfPTable(2); // 2 columns: Field & Value
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setWidths(new float[]{30f, 70f}); // Adjust column width

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font cellFont = new Font(Font.FontFamily.HELVETICA, 12);

            // Helper method to create table rows
            addTableRow(table, "Booking ID:", String.valueOf(booking.getId()), headerFont, cellFont);
            addTableRow(table, "Customer:", booking.getCustomer().getName(), headerFont, cellFont);
            addTableRow(table, "Driver:", booking.getDriver().getName(), headerFont, cellFont);
            addTableRow(table, "Vehicle:", booking.getVehicle().getModel(), headerFont, cellFont);
            addTableRow(table, "Pickup Location:", booking.getPickupLocation(), headerFont, cellFont);
            addTableRow(table, "Dropoff Location:", booking.getDropoffLocation(), headerFont, cellFont);

            // Fare row (highlighted with bold)
            Font fareFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.RED);
            PdfPCell fareLabel = new PdfPCell(new Phrase("Total Fare:", headerFont));
            fareLabel.setPadding(5);
            table.addCell(fareLabel);

            PdfPCell fareValue = new PdfPCell(new Phrase("Rs:" + booking.getFare(), fareFont));
            fareValue.setPadding(5);
            table.addCell(fareValue);

            document.add(table);
            document.add(new Paragraph("\n")); // Blank line

            // Footer with a Thank You note
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GRAY);
            Paragraph footer = new Paragraph("Thank you for choosing Mega City Cab! Safe Travels!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            // Timestamp
            Paragraph timestamp = new Paragraph("Generated on: " + new java.util.Date(), footerFont);
            timestamp.setAlignment(Element.ALIGN_RIGHT);
            document.add(timestamp);

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Failed to generate PDF", e);
        }
    }

    // Helper method to add rows to the table
    private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setPadding(5);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setPadding(5);
        table.addCell(valueCell);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getBookingsCount() {
        long count = bookingService.getTotalBookings();
        return ResponseEntity.ok(count);
    }
}