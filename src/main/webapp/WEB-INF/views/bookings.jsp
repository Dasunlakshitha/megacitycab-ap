<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment.megacitycab.models.Booking" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Management - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="flex">
    <!-- Include Sidebar -->
    <%@ include file="common/sidebar.jsp" %>

    <!-- Main Content -->
    <div class="flex-1 p-8">
        <h1 class="text-3xl font-bold mb-6">Booking Management</h1>

        <!-- Create Booking Button -->
        <div class="flex">
            <div class="mb-6 mr-8">
                <a href="/create-booking"
                   class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">
                    <i class="fas fa-plus mr-2"></i> Create Booking
                </a>

            </div>
            <div class="mb-6">
                <a href="/api/bookings/generate-pdf"
                   class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600">
                    <i class="fas fa-file-pdf mr-2"></i> Bookings Report
                </a>
            </div>
        </div>
        <!-- Bookings Table -->
        <div class="bg-white rounded-lg shadow-md overflow-hidden">
            <table class="min-w-full">
                <thead class="bg-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Customer</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Driver</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Vehicle</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Pickup</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Dropoff</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Fare</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
                </tr>
                </thead>
                <tbody class="divide-y divide-gray-200">
                <% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings"); %>
                <% if (bookings != null) {
                    for (Booking booking : bookings) {
                %>
                <tr>
                    <td class="px-6 py-4"><%= booking.getId() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getCustomer().getName() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getDriver().getName() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getVehicle().getModel() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getPickupLocation() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getDropoffLocation() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getFare() %>
                    </td>
                    <td class="px-6 py-4"><%= booking.getStatus() %>
                    </td>
                    <td class="px-6 py-4">
                        <a href="/edit-booking/<%= booking.getId() %>"
                           class="text-blue-500 hover:text-blue-700 mr-2">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="/delete-booking/<%= booking.getId() %>"
                           class="text-red-500 hover:text-red-700 mr-2">
                            <i class="fas fa-trash"></i>
                        </a>
                      <%--  <a href="/api/bookings/generate-pdf/<%= booking.getId() %>"
                           class="text-black-200 hover:text-blue-700 ">
                            <i class="fas fa-print"></i>
                        </a>--%>

                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>