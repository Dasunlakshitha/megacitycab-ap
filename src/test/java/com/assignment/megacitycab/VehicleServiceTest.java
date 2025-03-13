package com.assignment.megacitycab;

import com.assignment.megacitycab.models.Vehicle;
import com.assignment.megacitycab.repositories.VehicleRepository;
import com.assignment.megacitycab.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private Vehicle vehicle;
    private List<Vehicle> vehicleList;

    @BeforeEach
    void setUp() {
        // Setup test data
        vehicle = new Vehicle();
        vehicle.setVehicleID(1L);
        vehicle.setModel("Toyota Camry");
        vehicle.setCapacity(4);
        vehicle.setRegistrationNo("ABC123");
        vehicle.setStatus("AVAILABLE");
        vehicle.setDeleteStatus(false);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleID(2L);
        vehicle2.setModel("Honda Civic");
        vehicle2.setCapacity(5);
        vehicle2.setRegistrationNo("XYZ789");
        vehicle2.setStatus("IN_USE");
        vehicle2.setDeleteStatus(false);

        vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        vehicleList.add(vehicle2);
    }

    @Test
    void getAllVehicles_ShouldReturnAllNonDeletedVehicles() {
        // Arrange
        when(vehicleRepository.findByDeleteStatusFalse()).thenReturn(vehicleList);

        // Act
        List<Vehicle> result = vehicleService.getAllVehicles();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Toyota Camry", result.get(0).getModel());
        assertEquals("Honda Civic", result.get(1).getModel());
        verify(vehicleRepository, times(1)).findByDeleteStatusFalse();
    }

    @Test
    void getVehicleById_WithExistingId_ShouldReturnVehicle() {
        // Arrange
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        // Act
        Optional<Vehicle> result = vehicleService.getVehicleById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Toyota Camry", result.get().getModel());
        verify(vehicleRepository, times(1)).findById(1L);
    }

    @Test
    void getVehicleById_WithNonExistingId_ShouldReturnEmpty() {
        // Arrange
        when(vehicleRepository.findById(3L)).thenReturn(Optional.empty());

        // Act
        Optional<Vehicle> result = vehicleService.getVehicleById(3L);

        // Assert
        assertFalse(result.isPresent());
        verify(vehicleRepository, times(1)).findById(3L);
    }

    @Test
    void saveVehicle_ShouldSaveAndReturnVehicle() {
        // Arrange
        Vehicle newVehicle = new Vehicle();
        newVehicle.setModel("Ford Focus");
        newVehicle.setCapacity(5);
        newVehicle.setRegistrationNo("DEF456");
        newVehicle.setStatus("AVAILABLE");

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(newVehicle);

        // Act
        Vehicle result = vehicleService.saveVehicle(newVehicle);

        // Assert
        assertEquals("Ford Focus", result.getModel());
        verify(vehicleRepository, times(1)).save(newVehicle);
    }

    @Test
    void deleteVehicle_WithExistingId_ShouldSoftDeleteVehicle() {
        // Arrange
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        // Act
        vehicleService.deleteVehicle(1L);

        // Assert
        assertTrue(vehicle.isDeleteStatus());
        verify(vehicleRepository, times(1)).findById(1L);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void deleteVehicle_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(vehicleRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vehicleService.deleteVehicle(3L));
        verify(vehicleRepository, times(1)).findById(3L);
        verify(vehicleRepository, never()).save(any(Vehicle.class));
    }

    @Test
    void updateVehicle_WithExistingId_ShouldUpdateAndReturnVehicle() {
        // Arrange
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setModel("Toyota Corolla");
        updatedVehicle.setCapacity(5);
        updatedVehicle.setRegistrationNo("UPD123");
        updatedVehicle.setStatus("UNDER_MAINTENANCE");

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Vehicle result = vehicleService.updateVehicle(1L, updatedVehicle);

        // Assert
        assertEquals("Toyota Corolla", result.getModel());
        assertEquals(5, result.getCapacity());
        assertEquals("UPD123", result.getRegistrationNo());
        assertEquals("UNDER_MAINTENANCE", result.getStatus());
        verify(vehicleRepository, times(1)).findById(1L);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void updateVehicle_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Vehicle updatedVehicle = new Vehicle();
        when(vehicleRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vehicleService.updateVehicle(3L, updatedVehicle));
        verify(vehicleRepository, times(1)).findById(3L);
        verify(vehicleRepository, never()).save(any(Vehicle.class));
    }
}