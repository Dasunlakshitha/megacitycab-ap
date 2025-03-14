package com.assignment.megacitycab.services;

import com.assignment.megacitycab.models.Vehicle;
import com.assignment.megacitycab.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findByDeleteStatusFalse();
    }

    // Get vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Create or update vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Delete vehicle by ID
    public void deleteVehicle(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setDeleteStatus(true);
            vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setCapacity(updatedVehicle.getCapacity());
            vehicle.setRegistrationNo(updatedVehicle.getRegistrationNo());
            vehicle.setStatus(updatedVehicle.getStatus());
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

    public long vehicleCount() {
        return vehicleRepository.countByDeleteStatusFalse();
    }
}