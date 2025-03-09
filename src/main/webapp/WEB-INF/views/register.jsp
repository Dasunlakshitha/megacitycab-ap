<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta name="_csrf" content="${_csrf.token}">
    <%-- CSRF meta tag --%>
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <%-- CSRF header name meta tag --%>
</head>
<body>
<h1>Register</h1>

<form id="registrationForm" onsubmit="" action="/api/register" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Role:</label>
    <select id="role" name="role">
        <option value="USER">User</option>
        <option value="ADMIN">Admin</option>
    </select><br><br>

    <button type="submit">Register</button>
</form>

<a href="/login">Login</a>

<script>
    function registerUser(event) {
        event.preventDefault();

        const form = document.getElementById("registrationForm");
        const username = form.username.value;
        const password = form.password.value;
        const role = form.role.value;
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeaderName = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch('/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                    [csrfHeaderName]: csrfToken
            },
            body: JSON.stringify({username, password, role})
        })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 409) {
                        throw new Error("Username already exists.");
                    } else {
                        return response.text().then(err => {
                            throw new Error(err)
                        });
                    }
                }
                return response.json();
            })
            .then(data => {
                alert("Registration successful!");
                window.location.href = "/login";
            })
            .catch(error => {
                alert("Registration failed: " + error.message);
            });
    }
</script>

</body>
</html>