package com.assignment.megacitycab.repositories;

import com.assignment.megacitycab.models.User;
import com.assignment.megacitycab.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDeleteStatusFalse();
}