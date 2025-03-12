package com.assignment.megacitycab.repositories;

import com.assignment.megacitycab.models.Driver;
import com.assignment.megacitycab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByDeleteStatusFalse();
}