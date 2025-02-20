<!-- <%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Login - Mega City Cab</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg rounded-4">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Login to Mega City Cab</h3>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/register" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" id="username" name="username" class="form-control" required placeholder="Enter your username">
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" id="password" name="password" class="form-control" required placeholder="Enter your password">
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Login</button>
                    </form>
                    <div class="text-center mt-3">
                        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
                        <a href="index.jsp" class="btn btn-outline-secondary mt-2">Back to Home</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Mega City Cab</title>
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
            <h2>Login to Mega City Cab</h2>
        </div>
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/req/login" method="post">
                <div class="dialog-row text-center text-danger mb-3">
                    <% if (request.getParameter("error") != null) { %>
                    <label><%= session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") %></label>
                    <% } %>
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><ion-icon name="person-outline"></ion-icon></span>
                    <input name="username" id="username" type="text" class="form-control" required placeholder="Enter your username">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><ion-icon name="lock-closed-outline"></ion-icon></span>
                    <input name="password" type="password" id="password" class="form-control" required placeholder="Enter your password">
                </div>
                <div class="text-end mb-3">
                    <a href="#" class="text-primary">Forgot Password?</a>
                </div>
                <button type="submit" class="btn btn-primary w-100">Log in</button>
                <div class="text-center mt-3">
                    <p>Don't have an account? <a href="<%= request.getContextPath() %>/req/signup" class="text-primary">Register here</a></p>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<form action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Login">
</form>

<a href="/register">Register</a>

<%-- Display error messages if login fails --%>
<% if (request.getParameter("error") != null) { %>
    <p style="color: red;">Invalid username or password.</p>
<% } %>

</body>
</html>