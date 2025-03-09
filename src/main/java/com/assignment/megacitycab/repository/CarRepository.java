package com.assignment.megacitycab.repository;

import com.assignment.megacitycab.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCustomerId(String customerId);
}
