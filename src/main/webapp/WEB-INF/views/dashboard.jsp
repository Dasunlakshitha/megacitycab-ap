<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/12/25
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h2>Welcome to Your Dashboard</h2>
<p>You are logged in successfully.</p>
<a href="<%= request.getContextPath() %>/logout">Logout</a>
</body>
</html>
