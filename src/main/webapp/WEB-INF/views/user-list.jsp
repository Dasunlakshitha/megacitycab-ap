<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/22/25
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%-- JSTL for iteration --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>User List</title>
</head>
<body>
<h1>User List</h1>

<sec:authorize access="hasRole('ADMIN')"> <%-- Only admins can see this --%>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
                <%-- Add other columns as needed --%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">  <%-- Iterate through the users list --%>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.role}</td>
                    <%-- Add other data cells as needed --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</sec:authorize>

<sec:authorize access="!hasRole('ADMIN')">
    <p>You do not have permission to view this page</p>
</sec:authorize>

<a href="/home">Home</a> <%-- Link back to home page --%>

</body>
</html>
