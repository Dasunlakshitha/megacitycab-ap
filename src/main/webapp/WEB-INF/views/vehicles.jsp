<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment.megacitycab.models.Vehicle" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicle Management - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="flex">
    <!-- Include Sidebar -->
    <%@ include file="common/sidebar.jsp" %>

    <!-- Main Content -->
    <div class="flex-1 p-8">
        <h1 class="text-3xl font-bold mb-6">Vehicle Management</h1>

        <!-- Create Vehicle Button -->
        <div class="mb-6">
            <a href="/create-vehicle"
               class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">
                <i class="fas fa-plus mr-2"></i> Create Vehicle
            </a>
        </div>

        <!-- Vehicles Table -->
        <div class="bg-white rounded-lg shadow-md overflow-hidden">
            <table class="min-w-full">
                <thead class="bg-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Model</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Capacity</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Registration No</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
                </tr>
                </thead>
                <tbody class="divide-y divide-gray-200">
                <% List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles"); %>
                <% if(vehicles != null) {
                    for (Vehicle vehicle : vehicles) {
                %>
                <tr>
                    <td class="px-6 py-4"><%= vehicle.getId() %></td>
                    <td class="px-6 py-4"><%= vehicle.getModel() %></td>
                    <td class="px-6 py-4"><%= vehicle.getCapacity() %></td>
                    <td class="px-6 py-4"><%= vehicle.getRegistrationNo() %></td>
                    <td class="px-6 py-4"><%= vehicle.getStatus() %></td>
                    <td class="px-6 py-4">
                        <a href="/edit-vehicle/<%= vehicle.getId() %>"
                           class="text-blue-500 hover:text-blue-700 mr-2">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="/delete-vehicle/<%= vehicle.getId() %>"
                           class="text-red-500 hover:text-red-700">
                            <i class="fas fa-trash"></i>
                        </a>
                    </td>
                </tr>
                <% }} %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>