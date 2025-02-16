<%--
  Created by IntelliJ IDEA.
  User: ceydigitalsolutions
  Date: 2/13/25
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<h2>Add Customer</h2>
<form action="add-customer" method="post">
    <label>Name:</label>
    <input type="text" name="name" required><br>
    <label>Address:</label>
    <input type="text" name="address" required><br>
    <label>NIC:</label>
    <input type="text" name="nic" required><br>
    <label>Phone:</label>
    <input type="text" name="phone" required><br>
    <input type="submit" value="Save">
</form>
<br>
<a href="customers">Back to Customer List</a>
</body>
</html>

