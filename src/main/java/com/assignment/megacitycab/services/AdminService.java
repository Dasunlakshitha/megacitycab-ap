package com.assignment.megacitycab.services;

import com.assignment.megacitycab.models.Admin;
import com.assignment.megacitycab.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Create or Update Admin
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Get Admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Get All Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findByDeleteStatusFalse();
    }

    // Delete Admin by ID
    public void deleteAdmin(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setDeleteStatus(true);
            adminRepository.save(admin);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }
}