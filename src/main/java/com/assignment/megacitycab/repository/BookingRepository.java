package com.assignment.megacitycab.repository;

import com.assignment.megacitycab.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // ✅ This annotation tells Spring Boot it's a repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
