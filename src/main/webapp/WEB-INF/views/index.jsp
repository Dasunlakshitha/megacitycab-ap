<%--
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Mega City Cab</title>
</head>
<body>
<h1>Welcome to Mega City Cab</h1>
<a href="login">Login</a> | <a href="register">Register</a>
</body>
</html>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mega City Cab - Booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="card shadow-lg p-4">
        <h2 class="text-center text-primary mb-4">Mega City Cab Booking</h2>
        <form action="api/bookings" method="post" class="row g-3">
            <div class="col-md-6">
                <label class="form-label">Name</label>
                <input type="text" class="form-control" name="customerName" required>
            </div>
            <div class="col-md-6">
                <label class="form-label">Pickup Location</label>
                <input type="text" class="form-control" name="pickupLocation" required>
            </div>
            <div class="col-md-6">
                <label class="form-label">Drop Location</label>
                <input type="text" class="form-control" name="dropLocation" required>
            </div>
            <div class="col-md-6">
                <label class="form-label">Fare</label>
                <input type="number" class="form-control" name="fare" required>
            </div>
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary">Book Now</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

