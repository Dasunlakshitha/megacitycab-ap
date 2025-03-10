<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/23/25
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>MegaCityCab - Online Vehicle Reservation</title>
  <style>
    body {
      font-family: 'Arial', sans-serif; /* Modern font */
      margin: 0;
      padding: 0;
      background: linear-gradient(to bottom right, #e0f2f7, #bbdefb); /* Gradient background */
      color: #333; /* Dark text for contrast */
    }

    header {
      background-color: #0d47a1; /* Darker blue header */
      color: #fff;
      padding: 1em 0;
      text-align: center;
    }

    nav {
      background-color: #fff; /* White navigation */
      padding: 1em 0;
      text-align: center;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
    }

    nav a {
      display: inline-block;
      padding: 0.7em 1.2em; /* Slightly larger padding */
      margin: 0 0.7em;
      text-decoration: none;
      color: #0d47a1; /* Blue links */
      font-weight: 500; /* Slightly bolder font */
      transition: background-color 0.3s ease; /* Smooth transition */
      border-radius: 5px; /* Rounded corners */
    }

    nav a:hover {
      background-color: #e3f2fd; /* Lighter blue on hover */
    }

    main {
      padding: 3em;
      margin: 3em auto;
      max-width: 900px; /* Slightly wider main content */
      background-color: #fff;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.15); /* More prominent shadow */
      border-radius: 8px; /* More rounded corners */
    }

    footer {
      background-color: #0d47a1; /* Darker blue footer */
      color: #fff;
      padding: 1em 0;
      text-align: center;
      position: relative; /* Not fixed anymore */
      bottom: 0;
      width: 100%;
      margin-top: 3em; /* Add some space above the footer */
    }

    h1, h2 {
      text-align: center;
      color: #0d47a1; /* Blue headings */
    }

    .cta-button {
      display: block;
      margin: 25px auto;
      padding: 12px 25px;
      background-color: #0d47a1; /* Darker blue button */
      color: white;
      text-decoration: none;
      border-radius: 8px; /* More rounded corners */
      text-align: center;
      font-size: 1.1em; /* Slightly larger font */
      transition: background-color 0.3s ease; /* Smooth transition */
    }

    .cta-button:hover {
      background-color: #0b3983; /* Darker blue on hover */
    }

    .highlight {
      font-weight: 600;
      color: #0d47a1; /* Blue highlight */
    }

    /* Improved list styles */
    ul {
      list-style-type: disc;
      padding-left: 20px;
      margin-bottom: 1.5em; /* Add space between list items */
    }

    li {
      margin-bottom: 0.7em;
    }
  </style>
</head>
<body>

<header>
  <h1>MegaCityCab</h1>
</header>


<nav>
  <a href="/">Home</a>
  <a href="/about">About Us</a>
  <a href="/services">Services</a>
  <a href="/contact">Contact</a>
  <a href="/signin">Signin</a>
  <a href="/signup">Signup</a>
</nav>


<main>
  <h2>Welcome to MegaCityCab</h2>

  <p>
    Your premier online vehicle reservation service. We offer a convenient and reliable way to book transportation
    for all your needs.
    Whether you're traveling for business or pleasure, we have a wide range of vehicles to choose from, ensuring a
    comfortable and
    hassle-free journey.
  </p>

  <p>
    Experience the difference with MegaCityCab. We are committed to providing exceptional service and making your
    travel experience
    as smooth as possible. Our <span class="highlight">easy-to-use online booking system</span> allows you to
    reserve your ride in just a few clicks.
  </p>

  <a href="/book" class="cta-button">Book Your Ride Now</a>

  <h2>Our Services</h2>
  <ul>
    <li>Airport Transfers</li>
    <li>City Tours</li>
    <li>Corporate Travel</li>
    <li>Special Events</li>
  </ul>

  <h2>Why Choose Us?</h2>
  <ul>
    <li>Reliable and Punctual Service</li>
    <li>Wide Selection of Vehicles</li>
    <li>Competitive Pricing</li>
    <li>24/7 Customer Support</li>
  </ul>

</main>

<footer>
  &copy; 2024 MegaCityCab. All rights reserved.
</footer>

</body>
</html>
