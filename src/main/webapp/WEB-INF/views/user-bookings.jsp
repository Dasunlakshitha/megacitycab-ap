<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.assignment.megacitycab.models.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Booking - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h1 class="text-2xl font-bold mb-6 text-center">Create Booking</h1>

    <form id="createBookingForm" class="space-y-4">
        <div>
            <label class="block text-sm font-medium text-gray-700">User</label>
            <% User user = (User) request.getAttribute("user"); %>
            <% if (user != null) { %>
            <input type="text" value="<%= user.getName() %>" disabled
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
            <input type="hidden" id="userId" name="userId" value="<%= user.getId() %>">
            <% } else { %>
            <p class="text-red-500">User not found.</p>
            <% } %>
        </div>
        <div>
            <label for="pickupLocation" class="block text-sm font-medium text-gray-700">Pickup Location</label>
            <input type="text" id="pickupLocation" name="pickupLocation" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label for="dropoffLocation" class="block text-sm font-medium text-gray-700">Dropoff Location</label>
            <input type="text" id="dropoffLocation" name="dropoffLocation" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label for="fare" class="block text-sm font-medium text-gray-700">Fare</label>
            <input type="number" id="fare" name="fare" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm">
        </div>

        <div>
            <button type="submit" class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Create Booking
            </button>
        </div>
    </form>

    <div class="mt-4 text-center">
        <p class="text-sm text-gray-600">Go back to
            <a href="/" class="text-blue-500 hover:underline">Home</a>
        </p>
    </div>
</div>

<script>
    document.getElementById("createBookingForm").addEventListener("submit", async function(event) {
        event.preventDefault();

        const userId = document.getElementById("userId").value;
        const pickupLocation = document.getElementById("pickupLocation").value;
        const dropoffLocation = document.getElementById("dropoffLocation").value;
        const fare = document.getElementById("fare").value;
        const status = 'PENDING';

        const requestBody = JSON.stringify({
            customerId: parseInt(userId),
            pickupLocation,
            dropoffLocation,
            fare: parseFloat(fare),
            status
        });

        try {
            const response = await fetch("/api/bookings", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: requestBody
            });

            if (!response.ok) throw new Error("Failed to create booking.");

            alert("Booking created successfully!");
            window.location.href = "/";
        } catch (error) {
            console.error("Error:", error);
            alert("Error creating booking. Please check your inputs.");
        }
    });
</script>
</body>
</html>