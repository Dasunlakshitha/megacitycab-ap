package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.dtos.PaymentDTO;
import com.assignment.megacitycab.models.Booking;
import com.assignment.megacitycab.models.Payment;
import com.assignment.megacitycab.services.BookingService;
import com.assignment.megacitycab.services.PaymentService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BookingService bookingService;

    // Create or Update Payment
    @PostMapping
    public Payment savePayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.savePayment(paymentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.updatePayment(id, paymentDTO);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Delete Payment by ID
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    @GetMapping("/generate-pdf/{bookingId}")
    public void generateBillById(@PathVariable Long bookingId, HttpServletResponse response) throws IOException {
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
}