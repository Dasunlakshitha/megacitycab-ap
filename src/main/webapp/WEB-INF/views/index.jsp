<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Booking Page</title>
</head>
<body>
<h2>Welcome to Mega City Cab Booking System</h2>

<!-- Display existing bookings -->
<h3>Existing Bookings:</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Pickup</th>
        <th>Drop</th>
        <th>Fare</th>
    </tr>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.id}</td>
            <td>${booking.customerName}</td>
            <td>${booking.pickupLocation}</td>
            <td>${booking.dropLocation}</td>
            <td>${booking.fare}</td>
        </tr>
    </c:forEach>
</table>

<!-- Booking form -->
<form action="/api/bookings" method="post">
    <label>Name:</label> <input type="text" name="customerName"><br>
    <label>Pickup:</label> <input type="text" name="pickupLocation"><br>
    <label>Drop:</label> <input type="text" name="dropLocation"><br>
    <label>Fare:</label> <input type="text" name="fare"><br>
    <button type="submit">Book Now</button>
</form>

</body>
</html>
