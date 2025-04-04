package com.assignment.megacitycab.repositories;

import com.assignment.megacitycab.models.Admin;
import com.assignment.megacitycab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByDeleteStatusFalse();
}