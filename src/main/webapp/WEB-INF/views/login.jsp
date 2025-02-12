<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login - Mega City Cab</title>
</head>
<body>
<h2>Login to your account</h2>
<form action="<%=request.getContextPath()%>/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Register</button>
</form>
</body>
</html>
