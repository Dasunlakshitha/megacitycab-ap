<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/16/25
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Bookings</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center text-primary mb-4">Booking Records</h2>
        <table class="table table-bordered table-striped">
            <thead class="bg-primary text-white">
                <tr>
                    <th>ID</th>
                    <th>Customer Name</th>
                    <th>Pickup Location</th>
                    <th>Drop Location</th>
                    <th>Fare</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="bookingService" class="com.assignment.megacitycab.service.BookingService" scope="application"/>
                <%
                    java.util.List<com.assignment.megacitycab.model.Booking> bookings = bookingService.getAllBookings();
                    for (com.assignment.megacitycab.model.Booking booking : bookings) {
                %>
                <tr>
                    <td><%= booking.getId() %></td>
                    <td><%= booking.getCustomerName() %></td>
                    <td><%= booking.getPickupLocation() %></td>
                    <td><%= booking.getDropLocation() %></td>
                    <td>$<%= booking.getFare() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

