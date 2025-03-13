<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment.megacitycab.models.Vehicle" %>
<%@ page import="com.assignment.megacitycab.models.Driver" %>
<%@ page import="com.assignment.megacitycab.models.Customer" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Booking - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h1 class="text-2xl font-bold mb-6 text-center">Create Booking</h1>

    <!-- Create Booking Form -->
    <form id="createBookingForm" class="space-y-4">
        <div>
            <label for="customerId" class="block text-sm font-medium text-gray-700">Customer</label>
            <select id="customerId" name="customerId" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
                <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>
                <% if (customers != null) { %>
                <% for (Customer customer : customers) { %>
                <option value="<%= customer.getId() %>"><%= customer.getName() %> (ID: <%= customer.getId() %>)</option>
                <% } %>
                <% } else { %>
                <option value="">No Customers Available</option>
                <% } %>
            </select>
        </div>
        <div>
            <label for="driverId" class="block text-sm font-medium text-gray-700">Driver</label>
            <select id="driverId" name="driverId" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
                <% List<Driver> drivers = (List<Driver>) request.getAttribute("drivers"); %>
                <% if (drivers != null) { %>
                <% for (Driver driver : drivers) { %>
                <option value="<%= driver.getId() %>"><%= driver.getName() %></option>
                <% } %>
                <% } else { %>
                <option value="">No Drivers Available</option>
                <% } %>
            </select>
        </div>
        <div>
            <label for="vehicleId" class="block text-sm font-medium text-gray-700">Vehicle</label>
            <select id="vehicleId" name="vehicleId" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
                <% List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles"); %>
                <% if (vehicles != null) { %>
                <% for (Vehicle vehicle : vehicles) { %>
                <option value="<%= vehicle.getId() %>"><%= vehicle.getModel() %></option>
                <% } %>
                <% } else { %>
                <option value="">No Vehicles Available</option>
                <% } %>
            </select>
        </div>
        <div>
            <label for="pickupLocation" class="block text-sm font-medium text-gray-700">Pickup Location</label>
            <input type="text" id="pickupLocation" name="pickupLocation" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label for="dropoffLocation" class="block text-sm font-medium text-gray-700">Dropoff Location</label>
            <input type="text" id="dropoffLocation" name="dropoffLocation" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label for="fare" class="block text-sm font-medium text-gray-700">Fare</label>
            <input type="number" id="fare" name="fare" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
        </div>

        <div>
            <button type="submit" class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Create Booking
            </button>
        </div>
    </form>

    <div class="mt-4 text-center">
        <p class="text-sm text-gray-600">Go back to
            <a href="/bookings" class="text-blue-500 hover:underline">Booking Management</a>
        </p>
    </div>
</div>

<script>
    document.getElementById("createBookingForm").addEventListener("submit", async function(event) {
        event.preventDefault();

        const customerId = document.getElementById("customerId").value;
        const driverId = document.getElementById("driverId").value;
        const vehicleId = document.getElementById("vehicleId").value;
        const pickupLocation = document.getElementById("pickupLocation").value;
        const dropoffLocation = document.getElementById("dropoffLocation").value;
        const fare = document.getElementById("fare").value;
        const status = 'PENDING';

        if (!customerId || !driverId || !vehicleId) {
            alert("Please select all required fields.");
            return;
        }

        const requestBody = JSON.stringify({
            customerId: parseInt(customerId),
            driverId: parseInt(driverId),
            vehicleId: parseInt(vehicleId),
            pickupLocation,
            dropoffLocation,
            fare: parseFloat(fare),
            status
        });

        console.log("Request Payload:", requestBody); // Debugging log

        try {
            const response = await fetch("/api/bookings", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: requestBody
            });

            if (!response.ok) throw new Error("Failed to create booking.");

            const data = await response.json();
            alert("Booking created successfully!");
            window.location.href = "/bookings";
        } catch (error) {
            console.error("Error:", error);
            alert("Error creating booking. Please check your inputs.");
        }
    });
</script>
</body>
</html>
