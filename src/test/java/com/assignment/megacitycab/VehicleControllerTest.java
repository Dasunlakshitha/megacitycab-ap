package com.assignment.megacitycab;



import com.assignment.megacitycab.controllers.VehicleController;
import com.assignment.megacitycab.models.Vehicle;
import com.assignment.megacitycab.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();
        vehicle1.setVehicleID(1L);
        vehicle1.setModel("Toyota Camry");
        vehicle1.setCapacity(4);
        vehicle1.setRegistrationNo("ABC123");
        vehicle1.setStatus("AVAILABLE");

        vehicle2 = new Vehicle();
        vehicle2.setVehicleID(2L);
        vehicle2.setModel("Honda Civic");
        vehicle2.setCapacity(5);
        vehicle2.setRegistrationNo("XYZ789");
        vehicle2.setStatus("IN_USE");
    }

    @Test
    void getAllVehicles_ShouldReturnAllVehicles() throws Exception {
        when(vehicleService.getAllVehicles()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        mockMvc.perform(get("/api/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].model").value("Toyota Camry"))
                .andExpect(jsonPath("$[1].model").value("Honda Civic"));

        verify(vehicleService, times(1)).getAllVehicles();
    }

    @Test
    void getVehicleById_WithExistingId_ShouldReturnVehicle() throws Exception {
        // Arrange
        when(vehicleService.getVehicleById(1L)).thenReturn(Optional.of(vehicle1));

        // Act & Assert
        mockMvc.perform(get("/api/vehicles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Toyota Camry"))
                .andExpect(jsonPath("$.registrationNo").value("ABC123"));

        verify(vehicleService, times(1)).getVehicleById(1L);
    }

    @Test
    void getVehicleById_WithNonExistingId_ShouldReturnNotFound() throws Exception {
        // Arrange
        when(vehicleService.getVehicleById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/vehicles/3"))
                .andExpect(status().isNotFound());

        verify(vehicleService, times(1)).getVehicleById(3L);
    }

    @Test
    void saveVehicle_ShouldCreateAndReturnVehicle() throws Exception {
        // Arrange
        Vehicle newVehicle = new Vehicle();
        newVehicle.setModel("Ford Focus");
        newVehicle.setCapacity(5);
        newVehicle.setRegistrationNo("DEF456");
        newVehicle.setStatus("AVAILABLE");

        when(vehicleService.saveVehicle(any(Vehicle.class))).thenReturn(newVehicle);

        // Act & Assert
        mockMvc.perform(post("/api/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVehicle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Ford Focus"))
                .andExpect(jsonPath("$.registrationNo").value("DEF456"));

        verify(vehicleService, times(1)).saveVehicle(any(Vehicle.class));
    }

    @Test
    void updateVehicle_WithExistingId_ShouldUpdateAndReturnVehicle() throws Exception {
        // Arrange
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setModel("Toyota Corolla");
        updatedVehicle.setCapacity(5);
        updatedVehicle.setRegistrationNo("UPD123");
        updatedVehicle.setStatus("UNDER_MAINTENANCE");

        when(vehicleService.updateVehicle(eq(1L), any(Vehicle.class))).thenReturn(updatedVehicle);

        // Act & Assert
        mockMvc.perform(put("/api/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVehicle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Toyota Corolla"))
                .andExpect(jsonPath("$.registrationNo").value("UPD123"));

        verify(vehicleService, times(1)).updateVehicle(eq(1L), any(Vehicle.class));
    }

    @Test
    void updateVehicle_WithNonExistingId_ShouldReturnNotFound() throws Exception {
        // Arrange
        Vehicle updatedVehicle = new Vehicle();
        when(vehicleService.updateVehicle(eq(3L), any(Vehicle.class)))
                .thenThrow(new RuntimeException("Vehicle not found with id: 3"));

        // Act & Assert
        mockMvc.perform(put("/api/vehicles/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVehicle)))
                .andExpect(status().isNotFound());

        verify(vehicleService, times(1)).updateVehicle(eq(3L), any(Vehicle.class));
    }

    @Test
    void deleteVehicle_ShouldCallServiceMethod() throws Exception {
        // Arrange
        doNothing().when(vehicleService).deleteVehicle(1L);

        // Act & Assert
        mockMvc.perform(delete("/api/vehicles/1"))
                .andExpect(status().isOk());

        verify(vehicleService, times(1)).deleteVehicle(1L);
    }
}
