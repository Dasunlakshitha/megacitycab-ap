<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Vehicle - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h1 class="text-2xl font-bold mb-6 text-center">Create Vehicle</h1>

    <!-- Create Vehicle Form -->
    <form id="createVehicleForm" class="space-y-4">
        <div>
            <label for="model" class="block text-sm font-medium text-gray-700">Model</label>
            <input type="text" id="model" name="model" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="capacity" class="block text-sm font-medium text-gray-700">Capacity</label>
            <input type="number" id="capacity" name="capacity" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="registrationNo" class="block text-sm font-medium text-gray-700">Registration No</label>
            <input type="text" id="registrationNo" name="registrationNo" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="status" class="block text-sm font-medium text-gray-700">Status</label>
            <input type="text" id="status" name="status" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                Create Vehicle
            </button>
        </div>
    </form>

    <div class="mt-4 text-center">
        <p class="text-sm text-gray-600">Go back to
            <a href="/vehicles" class="text-blue-500 hover:underline">Vehicle Management</a>
        </p>
    </div>
</div>

<script>
    document.getElementById("createVehicleForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent form from submitting normally

        // Get form data
        const model = document.getElementById("model").value;
        const capacity = document.getElementById("capacity").value;
        const registrationNo = document.getElementById("registrationNo").value;
        const status = document.getElementById("status").value;

        try {
            const response = await fetch("/api/vehicles", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ model, capacity, registrationNo, status })
            });

            if (!response.ok) {
                throw new Error("Failed to create vehicle.");
            }

            const data = await response.json();
            alert("Vehicle created successfully!");

            // Redirect to vehicles page
            window.location.href = "/vehicles";
        } catch (error) {
            alert(error.message);
        }
    });
</script>
</body>
</html>