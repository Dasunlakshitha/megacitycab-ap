<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/20/25
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Home - Mega City Cab</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<header class="bg-primary text-white text-center py-5">
    <h1>Welcome to Mega City Cab</h1>
    <p>Reliable & Fast Cab Services at Your Fingertips</p>
    <a href="booking.jsp" class="btn btn-light btn-lg">Book a Ride</a>
</header>

<div class="container mt-5">
    <div class="row text-center">
        <div class="col-md-4">
            <div class="card shadow-lg p-4">
                <h3>Easy Booking</h3>
                <p>Book your ride with just a few clicks.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow-lg p-4">
                <h3>Safe & Secure</h3>
                <p>Our drivers are verified and trained professionals.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow-lg p-4">
                <h3>24/7 Support</h3>
                <p>We are available round the clock for assistance.</p>
            </div>
        </div>
    </div>
</div>

<footer class="bg-dark text-white text-center py-3 mt-5">
    <p>&copy; 2025 Mega City Cab. All Rights Reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
