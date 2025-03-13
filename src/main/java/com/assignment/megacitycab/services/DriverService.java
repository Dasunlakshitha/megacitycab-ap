package com.assignment.megacitycab.services;

import com.assignment.megacitycab.models.Driver;
import com.assignment.megacitycab.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // Create or Update Driver
    public Driver saveDriver(Driver driver) {
        driver.setRole("DRIVER");
        return driverRepository.save(driver);
    }

    // Get Driver by ID
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    // Get All Drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findByDeleteStatusFalse();
    }

    // Delete Driver by ID
    public void deleteDriver(Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            driver.setDeleteStatus(true);
            driverRepository.save(driver);
        } else {
            throw new RuntimeException("Driver not found with id: " + id);
        }
    }

    public Driver updateDriver(Long id, Driver updatedDriver) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setName(updatedDriver.getName());
            driver.setLicenseNo(updatedDriver.getLicenseNo());
            driver.setAvailability(updatedDriver.isAvailability());
            return driverRepository.save(driver);
        } else {
            throw new RuntimeException("Driver not found with id: " + id);
        }
    }

}