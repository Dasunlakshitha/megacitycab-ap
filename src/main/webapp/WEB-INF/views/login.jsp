<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login - Mega City Cab</title>
</head>
<body>
<h2>Login</h2>
<form action="<%= request.getContextPath() %>/login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required /><br/>

    <label>Password:</label>
    <input type="password" name="password" required /><br/>

    <button type="submit">Login</button>
</form>

<% if (request.getParameter("error") != null) { %>
<p style="color:red;">Invalid username or password</p>
<% } %>

<% if (request.getParameter("logout") != null) { %>
<p style="color:green;">You have been logged out successfully.</p>
<% } %>
</body>
</html>
