package com.assignment.megacitycab.repositories;

import com.assignment.megacitycab.models.Payment;
import com.assignment.megacitycab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDeleteStatusFalse();
}