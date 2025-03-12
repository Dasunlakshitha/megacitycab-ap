<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Driver - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h1 class="text-2xl font-bold mb-6 text-center">Create Driver</h1>

    <!-- Create Driver Form -->
    <form id="createDriverForm" class="space-y-4">
        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
            <input type="text" id="name" name="name" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="licenseNo" class="block text-sm font-medium text-gray-700">License No</label>
            <input type="text" id="licenseNo" name="licenseNo" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="availability" class="block text-sm font-medium text-gray-700">Availability</label>
            <select id="availability" name="availability" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                <option value="true">Available</option>
                <option value="false">Not Available</option>
            </select>
        </div>
        <div>
            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                Create Driver
            </button>
        </div>
    </form>

    <div class="mt-4 text-center">
        <p class="text-sm text-gray-600">Go back to
            <a href="/drivers" class="text-blue-500 hover:underline">Driver Management</a>
        </p>
    </div>
</div>

<script>
    document.getElementById("createDriverForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent form from submitting normally

        // Get form data
        const name = document.getElementById("name").value;
        const licenseNo = document.getElementById("licenseNo").value;
        const availability = document.getElementById("availability").value === "true";

        try {
            const response = await fetch("/api/drivers", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name, licenseNo, availability })
            });

            if (!response.ok) {
                throw new Error("Failed to create driver.");
            }

            const data = await response.json();
            alert("Driver created successfully!");

            // Redirect to drivers page
            window.location.href = "/drivers";
        } catch (error) {
            alert(error.message);
        }
    });
</script>
</body>
</html>