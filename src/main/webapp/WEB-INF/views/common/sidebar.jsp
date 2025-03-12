<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="bg-blue-800 text-white w-64 min-h-screen p-4">
    <h1 class="text-2xl font-bold mb-6">Dashboard</h1>
    <ul class="space-y-2">
        <li>
            <a href="/users" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-users mr-2"></i> User Management
            </a>
        </li>
        <li>
            <a href="/customers" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-user-friends mr-2"></i> Customer Management
            </a>
        </li>
        <li>
            <a href="/drivers" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-id-card mr-2"></i> Driver Management
            </a>
        </li>
        <li>
            <a href="/vehicles" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-car mr-2"></i> Vehicle Management
            </a>
        </li>
        <li>
            <a href="/bookings" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-calendar-alt mr-2"></i> Booking Management
            </a>
        </li>
        <li>
            <a href="/payments" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-credit-card mr-2"></i> Payment Management
            </a>
        </li>
        <li>
            <a href="#" onclick="confirmLogout()" class="block p-2 hover:bg-blue-700 rounded">
                <i class="fas fa-sign-out-alt mr-2"></i> Logout
            </a>
        </li>
    </ul>

</div>

<script>
    function confirmLogout() {
        if (confirm("Are you sure you want to logout?")) {
            window.location.href = "/login"; // Navigate to the login page
        }
    }
</script>