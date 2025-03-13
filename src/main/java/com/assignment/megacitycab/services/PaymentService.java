package com.assignment.megacitycab.services;

import com.assignment.megacitycab.dtos.PaymentDTO;
import com.assignment.megacitycab.models.Booking;
import com.assignment.megacitycab.models.Payment;
import com.assignment.megacitycab.repositories.BookingRepository;
import com.assignment.megacitycab.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Create or Update Payment
    @Autowired
    private BookingRepository bookingRepository;

    public Payment savePayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();

        // Find and set booking entity
        Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        payment.setBooking(booking);

        // Set other fields
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(paymentDTO.getStatus());

        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, PaymentDTO paymentDTO) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();

            // Update booking if provided
            if (paymentDTO.getBookingId() != null) {
                Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                        .orElseThrow(() -> new RuntimeException("Booking not found"));
                payment.setBooking(booking);
            }

            // Update other fields if they're provided
            if (paymentDTO.getAmount() > 0) {
                payment.setAmount(paymentDTO.getAmount());
            }

            if (paymentDTO.getPaymentMethod() != null) {
                payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            }

            if (paymentDTO.getStatus() != null) {
                payment.setStatus(paymentDTO.getStatus());
            }

            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    // Get Payment by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findByDeleteStatusFalse();
    }

    // Delete Payment by ID
    public void deletePayment(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setDeleteStatus(true);
            paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

}