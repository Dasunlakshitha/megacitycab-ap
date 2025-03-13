<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Booking Home</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        // Function to check if the email cookie exists
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


        // Function to handle logout
        function logout() {
            document.cookie = "email=; path=/; max-age=0"; // Delete the email cookie
            window.location.href = "/"; // Redirect to home page
        }
    </script>
</head>
<body class="bg-gray-100">

<!-- Navigation Bar -->
<nav class="bg-white shadow-lg">
    <div class="max-w-6xl mx-auto px-4">
        <div class="flex justify-between">
            <div class="flex space-x-7">
                <div>
                    <!-- Website Logo -->
                    <a href="#" class="flex items-center py-4 px-2">
                        <span class="font-semibold text-gray-500 text-lg">CarBooking</span>
                    </a>
                </div>
                <!-- Primary Navbar items -->
                <div class="hidden md:flex items-center space-x-1">

                    <a href="#guidelines" class="py-4 px-2 text-gray-500 font-semibold hover:text-green-500 transition duration-300">Guidelines</a>
                    <script>
                        if (getCookie("email")) {
                            const userEmail = getCookie("email");

// Append email as a query parameter if it exists
                            const userBookingUrl = "/user-bookings" + (userEmail ? "?email=" + encodeURIComponent(userEmail) : "");
                            const myBookingsUrl = "/my-bookings" + (userEmail ? "?email=" + encodeURIComponent(userEmail) : "");

// Write links dynamically
                            document.write(
                                '<a href="' + userBookingUrl + '" class="py-4 px-2 text-gray-500 font-semibold hover:text-green-500 transition duration-300">Bookings</a>' +
                                '<a href="' + myBookingsUrl + '" class="py-4 px-2 text-gray-500 font-semibold hover:text-green-500 transition duration-300">My Bookings</a>'
                            );
                        }
                    </script>
                </div>
            </div>
            <!-- Secondary Navbar items -->
            <div class="hidden md:flex items-center space-x-3">
                <script>
                    if (getCookie("email")) {
                        document.write(`

                            <button onclick="logout()" class="py-2 px-2 font-medium text-white bg-red-500 rounded hover:bg-red-400 transition duration-300">Logout</button>
                        `);
                    } else {
                        document.write(`
                            <a href="/login" class="py-2 px-2 font-medium text-white bg-green-500 rounded hover:bg-green-400 transition duration-300">Login</a>
                        `);
                    }
                </script>
            </div>
        </div>
    </div>
</nav>

<!-- Hero Section with Gradient Background -->
<div class="bg-gradient-to-r from-green-400 to-blue-500">
    <div class="max-w-4xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:px-8">
        <div class="text-center">
            <h1 class="text-4xl font-extrabold text-white sm:text-5xl md:text-6xl">
                <span class="block">Book Your Dream Car</span>
                <span class="block text-green-200">Anywhere, Anytime</span>
            </h1>
            <p class="mt-3 max-w-md mx-auto text-base text-white sm:text-lg md:mt-5 md:text-xl md:max-w-3xl">
                Discover the best car rental deals and enjoy a seamless booking experience with us.
            </p>
            <div class="mt-5 max-w-md mx-auto sm:flex sm:justify-center md:mt-8">
                <div class="rounded-md shadow">
                    <a href="#" class="w-full flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-white bg-green-600 hover:bg-green-700 md:py-4 md:text-lg md:px-10">
                        Get Started
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Guidelines Section -->
<div id="guidelines" class="max-w-4xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:px-8">
    <h2 class="text-3xl font-extrabold text-gray-900">Guidelines for Booking</h2>
    <div class="mt-6 space-y-6">
        <div>
            <h3 class="text-xl font-semibold text-gray-800">1. Choose Your Vehicle</h3>
            <p class="mt-2 text-gray-600">Select the vehicle that best suits your needs from our wide range of options.</p>
        </div>
        <div>
            <h3 class="text-xl font-semibold text-gray-800">2. Provide Pickup and Dropoff Locations</h3>
            <p class="mt-2 text-gray-600">Enter your pickup and dropoff locations to get accurate fare estimates.</p>
        </div>
        <div>
            <h3 class="text-xl font-semibold text-gray-800">3. Confirm Your Booking</h3>
            <p class="mt-2 text-gray-600">Review your booking details and confirm to secure your vehicle.</p>
        </div>
        <div>
            <h3 class="text-xl font-semibold text-gray-800">4. Enjoy Your Ride</h3>
            <p class="mt-2 text-gray-600">Sit back, relax, and enjoy your journey with our reliable service.</p>
        </div>
    </div>
</div>

</body>
</html>