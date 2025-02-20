<%--<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Register - Mega City Cab</title>
</head>
<body>
<h2>Create a new account</h2>
<form action="<%=request.getContextPath()%>/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

    <button type="submit">Register</button>
</form>
</body>
</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup - Mega City Cab</title>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <script src="<%= request.getContextPath() %>/js/signup.js" defer></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="card shadow-lg p-4 rounded-4 w-50">
        <div class="card-header bg-primary text-white text-center">
            <h2>Sign Up</h2>
        </div>
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/req/signup" method="post">
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><ion-icon name="person-outline"></ion-icon></span>
                    <input type="text" id="username" name="username" class="form-control" required placeholder="Enter your name">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><ion-icon name="mail-outline"></ion-icon></span>
                    <input type="email" id="email" name="email" class="form-control" required placeholder="Enter your email">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><ion-icon name="lock-closed-outline"></ion-icon></span>
                    <input type="password" id="password" name="password" class="form-control" required placeholder="Enter your password">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><ion-icon name="lock-closed-outline"></ion-icon></span>
                    <input type="password" id="passwordcon" name="passwordcon" class="form-control" required placeholder="Confirm your password">
                </div>
                <button id="submit" type="submit" class="btn btn-primary w-100">Sign Up</button>
                <div class="text-center mt-3">
                    <p>Already have an account? <a href="<%= request.getContextPath() %>/req/login" class="text-primary">Log In</a></p>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const signupForm = document.querySelector('form');
        signupForm.style.opacity = 0;
        setTimeout(() => {
            signupForm.style.transition = 'opacity 1s ease-in-out';
            signupForm.style.opacity = 1;
        }, 500);

        const submitButton = document.getElementById("submit");
        submitButton.addEventListener('click', (event) => {
            const username = document.getElementById('username').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('passwordcon').value;

            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                event.preventDefault();
            }
        });
    });
</script>
</body>
</html>

