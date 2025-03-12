package com.assignment.megacitycab.repositories;

import com.assignment.megacitycab.models.Customer;
import com.assignment.megacitycab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByDeleteStatusFalse();
}