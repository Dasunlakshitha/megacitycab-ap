<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.assignment.megacitycab.models.Booking" %>
            <%@ page import="com.assignment.megacitycab.models.User" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>My Bookings - Car Booking System</title>
                    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
                    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
                        rel="stylesheet">
                </head>

                <body class="bg-gray-100">
                    <div class="flex">


                        <!-- Main Content -->
                        <div class="flex-1 p-8">
                            <h1 class="text-3xl font-bold mb-6">My Bookings</h1>

                            <!-- Display User Info -->
                            <% User user=(User) request.getAttribute("user"); %>
                                <% if (user !=null) { %>
                                    <div class="mb-6 p-4 bg-white shadow-md rounded-lg">
                                        <p class="text-lg font-semibold">Welcome, <%= user.getName() %>!</p>
                                        <p class="text-gray-600">Email: <%= user.getEmail() %>
                                        </p>
                                    </div>
                                    <% } %>

                                        <!-- Bookings Table -->
                                        <div class="bg-white rounded-lg shadow-md overflow-hidden">
                                            <table class="min-w-full">
                                                <thead class="bg-gray-200">
                                                    <tr>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Booking ID</th>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Driver</th>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Vehicle</th>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Pickup</th>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Dropoff</th>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Fare</th>
                                                        <th
                                                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                                                            Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody class="divide-y divide-gray-200">
                                                    <% List<Booking> bookings = (List<Booking>)
                                                            request.getAttribute("bookings"); %>
                                                            <% if (bookings !=null && !bookings.isEmpty()) { for
                                                                (Booking booking : bookings) { %>
                                                                <tr>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getId() %>
                                                                    </td>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getDriver() !=null ?
                                                                            booking.getDriver().getName()
                                                                            : "No Driver Assigned" %>
                                                                    </td>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getVehicle() !=null ?
                                                                            booking.getVehicle().getModel()
                                                                            : "No Vehicle Assigned" %>
                                                                    </td>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getPickupLocation() %>
                                                                    </td>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getDropoffLocation() %>
                                                                    </td>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getFare() %>
                                                                    </td>
                                                                    <td class="px-6 py-4">
                                                                        <%= booking.getStatus() %>
                                                                    </td>
                                                                </tr>
                                                                <% }} else { %>
                                                                    <tr>
                                                                        <td colspan="7"
                                                                            class="text-center px-6 py-4 text-gray-500">
                                                                            No bookings found.</td>
                                                                    </tr>
                                                                    <% } %>
                                                </tbody>
                                            </table>
                                        </div>
                        </div>
                    </div>
                </body>

                </html>