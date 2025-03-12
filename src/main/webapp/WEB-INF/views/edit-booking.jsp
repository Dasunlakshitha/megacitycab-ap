<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assignment.megacitycab.models.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment.megacitycab.models.Vehicle" %>
<%@ page import="com.assignment.megacitycab.models.Driver" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Booking - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h1 class="text-2xl font-bold mb-6 text-center">Edit Booking</h1>

    <!-- Edit Booking Form -->
    <form id="editBookingForm" class="space-y-4">
        <div>
            <label for="customerId" class="block text-sm font-medium text-gray-700">Customer ID</label>
            <input type="text" id="customerId" name="customerId" value="<%= ((Booking) request.getAttribute("booking")).getCustomer().getId() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="driverId" class="block text-sm font-medium text-gray-700">Driver</label>
            <select id="driverId" name="driverId" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                <% List<Driver> drivers = (List<Driver>) request.getAttribute("drivers"); %>
                <% if (drivers != null) {
                    for (Driver driver : drivers) { %>
                <option value="<%= driver.getId() %>" <%= driver.getId().equals(((Booking) request.getAttribute("booking")).getDriver().getId()) ? "selected" : "" %>>
                    <%= driver.getName() %>
                </option>
                <% }
                } %>
            </select>
        </div>
        <div>
            <label for="vehicleId" class="block text-sm font-medium text-gray-700">Vehicle</label>
            <select id="vehicleId" name="vehicleId" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                <% List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles"); %>
                <% if (vehicles != null) {
                    for (Vehicle vehicle : vehicles) { %>
                <option value="<%= vehicle.getId() %>" <%= vehicle.getId().equals(((Booking) request.getAttribute("booking")).getVehicle().getId()) ? "selected" : "" %>>
                    <%= vehicle.getModel() %>
                </option>
                <% }
                } %>
            </select>
        </div>
        <div>
            <label for="pickupLocation" class="block text-sm font-medium text-gray-700">Pickup Location</label>
            <input type="text" id="pickupLocation" name="pickupLocation" value="<%= ((Booking) request.getAttribute("booking")).getPickupLocation() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="dropoffLocation" class="block text-sm font-medium text-gray-700">Dropoff Location</label>
            <input type="text" id="dropoffLocation" name="dropoffLocation" value="<%= ((Booking) request.getAttribute("booking")).getDropoffLocation() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="fare" class="block text-sm font-medium text-gray-700">Fare</label>
            <input type="number" id="fare" name="fare" value="<%= ((Booking) request.getAttribute("booking")).getFare() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="status" class="block text-sm font-medium text-gray-700">Status</label>
            <select id="status" name="status" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                <option value="PENDING" <%= "PENDING".equals(((Booking) request.getAttribute("booking")).getStatus()) ? "selected" : "" %>>Pending</option>
                <option value="COMPLETED" <%= "COMPLETED".equals(((Booking) request.getAttribute("booking")).getStatus()) ? "selected" : "" %>>Completed</option>
            </select>
        </div>
        <div>
            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                Update Booking
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
    document.getElementById("editBookingForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent form from submitting normally

        // Get form data
        const customerId = document.getElementById("customerId").value;
        const driverId = document.getElementById("driverId").value;
        const vehicleId = document.getElementById("vehicleId").value;
        const pickupLocation = document.getElementById("pickupLocation").value;
        const dropoffLocation = document.getElementById("dropoffLocation").value;
        const fare = document.getElementById("fare").value;
        const status = document.getElementById("status").value;

        try {
            const response = await fetch("/api/bookings/<%= ((Booking) request.getAttribute("booking")).getId() %>", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ customerId, driverId, vehicleId, pickupLocation, dropoffLocation, fare, status })
            });

            if (!response.ok) {
                throw new Error("Failed to update booking.");
            }

            const data = await response.json();
            alert("Booking updated successfully!");

            // Redirect to bookings page
            window.location.href = "/bookings";
        } catch (error) {
            alert(error.message);
        }
    });
</script>
</body>
</html>