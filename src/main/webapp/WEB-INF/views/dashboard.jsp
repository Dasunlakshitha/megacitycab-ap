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
        <h1 class="text-3xl font-bold mb-6">Welcome to the Car Booking System</h1>
        <p class="text-gray-700">Select a section from the sidebar to manage the system.</p>
    </div>


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
</body>
</html>