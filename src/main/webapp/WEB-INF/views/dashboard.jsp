<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Car Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="flex">
    <!-- Sidebar -->
    <div class="bg-blue-800 text-white w-64 min-h-screen p-4">
        <h1 class="text-2xl font-bold mb-6">Dashboard</h1>
        <ul class="space-y-2">
            <li>
                <a href="/users" class="block p-2 hover:bg-blue-700 rounded">User Management</a>
            </li>
            <li>
                <a href="/customers" class="block p-2 hover:bg-blue-700 rounded">Customer Management</a>
            </li>
            <li>
                <a href="/drivers" class="block p-2 hover:bg-blue-700 rounded">Driver Management</a>
            </li>
            <li>
                <a href="/vehicles" class="block p-2 hover:bg-blue-700 rounded">Vehicle Management</a>
            </li>
            <li>
                <a href="/bookings" class="block p-2 hover:bg-blue-700 rounded">Booking Management</a>
            </li>
            <li>
                <a href="/payments" class="block p-2 hover:bg-blue-700 rounded">Payment Management</a>
            </li>
            <li>
                <a href="#" onclick="confirmLogout()" class="block p-2 hover:bg-blue-700 rounded">
                    <i class="fas fa-sign-out-alt mr-2"></i> Logout
                </a>
            </li>
        </ul>
    </div>
    <!-- Main Content -->
    <div class="flex-1 p-8">
        <h1 class="text-3xl font-bold mb-6">Dashboard Overview</h1>

        <!-- Dashboard Stats -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
            <div class="bg-white p-4 shadow rounded-lg text-center">
                <h2 class="text-xl font-semibold">Total Users</h2>
                <p id="totalUsers" class="text-2xl font-bold text-blue-700">0</p>
            </div>
            <div class="bg-white p-4 shadow rounded-lg text-center">
                <h2 class="text-xl font-semibold">Total Customers</h2>
                <p id="totalCustomers" class="text-2xl font-bold text-blue-700">0</p>
            </div>
            <div class="bg-white p-4 shadow rounded-lg text-center">
                <h2 class="text-xl font-semibold">Total Bookings</h2>
                <p id="totalBookings" class="text-2xl font-bold text-green-700">0</p>
            </div>
            <div class="bg-white p-4 shadow rounded-lg text-center">
                <h2 class="text-xl font-semibold">Total Vehicles</h2>
                <p id="totalVehicles" class="text-2xl font-bold text-orange-700">0</p>
            </div>
            <div class="bg-white p-4 shadow rounded-lg text-center">
                <h2 class="text-xl font-semibold">Total Drivers</h2>
                <p id="totalDrivers" class="text-2xl font-bold text-red-700"><%= request.getAttribute("totalDrivers") %></p>
            </div>
        </div>

        <div id="content" class="bg-white p-6 shadow rounded-lg mt-6">
            <p>Select a section from the sidebar to manage the system.</p>
        </div>
    </div>
</div>
   <%-- <!-- Main Content -->
    <div class="flex-1 p-8">
        <h1 class="text-3xl font-bold mb-6">Welcome to the Car Booking System</h1>
        <p class="text-gray-700">Select a section from the sidebar to manage the system.</p>
    </div>--%>


</div>
<script>
    // Get a cookie
    function getCookie(name) {
        const cname = name + "=";
        const decodedCookie = decodeURIComponent(document.cookie);
        const ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(cname) === 0) {
                return c.substring(cname.length, c.length);
            }
        }
        return "";
    }

    function checkUser(){
        const role =  getCookie("role")
        console.log(role)
        if(role!=="ADMIN"){
            window.location.href = "/";
        }
    }
    checkUser();
    function confirmLogout() {
        if (confirm("Are you sure you want to logout?")) {
            window.location.href = "/";
            deleteCookie("email");
            deleteCookie("role");
        }
    }
    function deleteCookie(name) {
        document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    }
</script>
<script>
    function fetchUserCount() {
        fetch('/api/users/count')
            .then(response => response.json())
            .then(data => {
                document.getElementById('totalUsers').innerText = data;
            })
            .catch(error => console.error('Error fetching user count:', error));
    }

    fetchUserCount();
</script>
<script>
    function fetchUserCount() {
        fetch('/api/customers/count')
            .then(response => response.json())
            .then(data => {
                document.getElementById('totalCustomers').innerText = data;
            })
            .catch(error => console.error('Error fetching user count:', error));
    }

    fetchUserCount();
</script>
<script>
    function fetchUserCount() {
        fetch('/api/bookings/count')
            .then(response => response.json())
            .then(data => {
                document.getElementById('totalBookings').innerText = data;
            })
            .catch(error => console.error('Error fetching user count:', error));
    }

    fetchUserCount();
</script>
<script>
    function fetchUserCount() {
        fetch('/api/drivers/count')
            .then(response => response.json())
            .then(data => {
                document.getElementById('totalDrivers').innerText = data;
            })
            .catch(error => console.error('Error fetching user count:', error));
    }

    fetchUserCount();
</script>
<script>
    function fetchUserCount() {
        fetch('/api/vehicles/count')
            .then(response => response.json())
            .then(data => {
                document.getElementById('totalVehicles').innerText = data;
            })
            .catch(error => console.error('Error fetching user count:', error));
    }

    fetchUserCount();
</script>
</body>
</html>