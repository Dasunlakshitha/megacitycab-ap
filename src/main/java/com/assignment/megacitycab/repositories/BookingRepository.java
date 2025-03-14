package com.assignment.megacitycab.repositories;

import com.assignment.megacitycab.models.Booking;
import com.assignment.megacitycab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDeleteStatusFalse();
    long countByDeleteStatusFalse();
}