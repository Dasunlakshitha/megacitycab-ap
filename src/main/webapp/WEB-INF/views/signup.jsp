<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/23/25
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>MegaCityCab - Sign Up</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(to bottom right, #e0f2f7, #bbdefb);
            color: #333;
            display: flex; /* Center the form vertically and horizontally */
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* Ensure full viewport height */
        }

        .signup-container {
            background-color: #fff;
            padding: 3em;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
            border-radius: 8px;
            width: 400px; /* Adjust width as needed */
        }

        h2 {
            text-align: center;
            color: #0d47a1;
            margin-bottom: 1.5em;
        }

        label {
            display: block;
            margin-bottom: 0.5em;
            color: #0d47a1;
            font-weight: 500;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: 100%;
            padding: 0.8em;
            margin-bottom: 1.2em;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 1em;
        }

        button {
            background-color: #0d47a1;
            color: white;
            padding: 1em 1.5em;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 1.1em;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0b3983;
        }

        .error-message {
            color: red;
            margin-top: 1em;
            text-align: center;
        }

        .login-link {
            text-align: center;
            margin-top: 1.5em;
        }

        .login-link a {
            color: #0d47a1;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return false; // Prevent form submission
            }
            return true; // Allow form submission if passwords match
        }
    </script>
</head>

<body>

<div class="signup-container">
    <h2>Sign Up</h2>

    <form action="/sign-up" method="post" onsubmit="return validateForm();">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirm" required>


        <label for="role">Role:</label>
        <select id="role" name="user_role">
            <option value="CUSTOMER">Customer</option>
            <option value="DRIVER">Driver</option>
            <option value="ADMIN">Admin</option>
        </select>

        <button type="submit">Sign Up</button>

        <div class="error-message">
            <c:if test="${not empty param.error}">
                <p>${param.error}</p>
            </c:if>
        </div>

        <div class="login-link">
            Already have an account? <a href="/login">Login</a>
        </div>
    </form>
</div>

</body>
</html>