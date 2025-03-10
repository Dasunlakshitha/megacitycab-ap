package com.assignment.megacitycab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    private boolean deleteStatus = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private double amount;
    private String paymentMethod; // e.g., CASH, CARD, ONLINE
    private String status; // e.g., PENDING, COMPLETED, FAILED

    public Booking getBooking(){
        return this.booking;
    }

    public void setBooking(Booking b){
        this.booking = b;
    }
}
