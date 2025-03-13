<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assignment.megacitycab.models.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-md w-96">
    <h1 class="text-2xl font-bold mb-6 text-center">Edit User</h1>

    <!-- Edit User Form -->
    <form id="editUserForm" class="space-y-4">
        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
            <input type="text" id="name" name="name" value="<%= ((User) request.getAttribute("user")).getName() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="phone" class="block text-sm font-medium text-gray-700">Phone</label>
            <input type="text" id="phone" name="phone" value="<%= ((User) request.getAttribute("user")).getPhone() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="email" id="email" name="email" value="<%= ((User) request.getAttribute("user")).getEmail() %>" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="role" class="block text-sm font-medium text-gray-700">Role</label>
            <select id="role" name="role" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                <option value="ADMIN" <%= ((User) request.getAttribute("user")).getRole().equals("ADMIN") ? "selected" : "" %>>Admin</option>
                <option value="CUSTOMER" <%= ((User) request.getAttribute("user")).getRole().equals("CUSTOMER") ? "selected" : "" %>>Customer</option>
                <option value="DRIVER" <%= ((User) request.getAttribute("user")).getRole().equals("DRIVER") ? "selected" : "" %>>Driver</option>
            </select>
        </div>
        <div>
            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                Update User
            </button>
        </div>
    </form>

    <div class="mt-4 text-center">
        <p class="text-sm text-gray-600">Go back to
            <a href="/users" class="text-blue-500 hover:underline">User Management</a>
        </p>
    </div>
</div>

<script>
    document.getElementById("editUserForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent form from submitting normally

        // Get form data
        const name = document.getElementById("name").value;
        const phone = document.getElementById("phone").value;
        const email = document.getElementById("email").value;
        const role = document.getElementById("role").value;

        try {
            const response = await fetch("/api/users/<%= ((User) request.getAttribute("user")).getId() %>", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name, phone, email, role })
            });

            if (!response.ok) {
                throw new Error("Failed to update user.");
            }

            const data = await response.json();
            alert("User updated successfully!");

            // Redirect to users page
            window.location.href = "/users";
        } catch (error) {
            alert(error.message);
        }
    });
</script>
</body>
</html>