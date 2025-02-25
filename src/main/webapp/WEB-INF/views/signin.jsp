<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MegaCityCab - Sign In</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(to bottom right, #e0f2f7, #bbdefb);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .signin-container {
            background-color: #fff;
            padding: 3em;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
            border-radius: 8px;
            width: 400px;
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
        input[type="password"] {
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

        .signup-link {
            text-align: center;
            margin-top: 1.5em;
        }

        .signup-link a {
            color: #0d47a1;
            text-decoration: none;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="signin-container">
    <h2>${role} Sign In</h2>

    <form action="/sign-in" method="post">
        <input type="hidden" name="role" id="roleInput">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Sign In</button>

        <div class="error-message">
            <c:if test="${not empty param.error}">
                <p>${param.error}</p>
            </c:if>
        </div>

    </form>
    <form id="signupForm" action="/signup" method="post">
        <input type="hidden" name="role" value="${role}">
        <c:if test="${role != 'ADMIN'}">
            <div class="signup-link">
                Don't have an account? <a href="#"
                                          onclick="document.getElementById('signupForm').submit(); return false;">Sign
                Up</a>
            </div>
        </c:if>

    </form>
</div>
</body>
</html>
