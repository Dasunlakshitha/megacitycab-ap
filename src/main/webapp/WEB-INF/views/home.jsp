<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome to the Home Page!</h1>

<sec:authorize access="isAuthenticated()"> <%-- Show only if logged in --%>
    <p>Welcome, <sec:authentication property="name"/>!</p> <%-- Use Spring Security tag --%>

    <sec:authorize access="hasRole('ADMIN')"> <%-- Admin-specific content --%>
        <h2>Admin Dashboard</h2>
        <p>This content is only visible to administrators.</p>
        <%-- Add admin-specific links or content here --%>
        <a href="/admin/dashboard">Admin Dashboard Link</a> <br> <%-- Example --%>

    </sec:authorize>

    <sec:authorize access="hasRole('DRIVER')"> <%-- Driver-specific content --%>
        <h2>Driver Dashboard</h2>
        <p>This content is only visible to drivers.</p>
        <%-- Add driver-specific links or content here --%>
        <a href="/driver/dashboard">Driver Dashboard Link</a><br> <%-- Example --%>
    </sec:authorize>

    <sec:authorize access="hasRole('CUSTOMER')"> <%-- Customer-specific content --%>
        <h2>Customer Dashboard</h2>
        <p>This content is only visible to customers.</p>
        <%-- Add customer-specific links or content here --%>
        <a href="/customer/dashboard">Customer Dashboard Link</a><br> <%-- Example --%>
    </sec:authorize>

    <a href="/logout">Logout</a>
</sec:authorize>

<sec:authorize access="!isAuthenticated()"> <%-- Show only if NOT logged in --%>
    <p>Please <a href="/login">login</a> or <a href="/register">register</a>.</p>
</sec:authorize>



</body>
</html>