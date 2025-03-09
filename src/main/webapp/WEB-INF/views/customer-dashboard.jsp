<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard - MegaCityCab</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: 'Arial', sans-serif;
      margin: 0;
      padding: 0;
      background: linear-gradient(to bottom right, #e0f2f7, #bbdefb);
      color: #333;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }

    .sidebar {
      width: 100%;
      background-color: #0d47a1;
      padding: 20px;
      color: white;
      position: relative;
      height: auto;
      text-align: center;
    }

    .sidebar h2 {
      margin-bottom: 20px;
    }

    .sidebar ul {
      list-style: none;
      padding: 0;
      display: flex;
      flex-wrap: wrap;
      justify-content: center; /* Center the list items */
    }

    .sidebar ul li {
      margin: 10px;
    }

    .sidebar ul li a {
      text-decoration: none;
      color: white;
      display: block;
      padding: 10px 15px; /* Increased padding */
      border-radius: 5px;
      transition: background 0.3s;
    }

    .sidebar ul li a:hover {
      background-color: #0b3983;
    }

    .content {
      padding: 30px; /* Increased padding */
      width: 100%;
      box-sizing: border-box;
      flex-grow: 1;
    }

    .dashboard-cards {
      display: flex;
      flex-wrap: wrap;
      gap: 30px; /* Increased gap */
      justify-content: center; /* Center the cards */
    }

    .card {
      background-color: white;
      padding: 25px; /* Increased padding */
      border-radius: 8px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
      flex: 1 1 350px; /* Adjusted flex-basis */
      text-align: center;
    }

    .card h3 {
      margin-bottom: 15px; /* Added margin */
    }

    .logout {
      position: absolute;
      top: 20px;
      right: 20px;
      width: auto;
      text-align: center;
      margin: 0;
    }

    .logout a {
      color: white;
      text-decoration: none;
      display: block;
      background-color: #ff3d00;
      padding: 10px 15px; /* Increased padding */
      border-radius: 5px;
      transition: background 0.3s;
      font-size: 16px; /* Increased font size */
    }

    .admin-actions {
      margin-top: 30px; /* Increased margin */
      display: flex;
      flex-wrap: wrap;
      gap: 20px; /* Increased gap */
      justify-content: center;
    }

    .admin-actions button {
      background-color: #0d47a1;
      color: white;
      padding: 12px 20px; /* Increased padding */
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background 0.3s;
      font-size: 16px; /* Increased font size */
    }

    .admin-actions button:hover {
      background-color: #0b3983;
    }

    @media (max-width: 768px) {
      .sidebar ul {
        flex-direction: column;
      }

      .card {
        flex: 1 1 100%;
      }

      .logout {
        position: relative;
        top: auto;
        right: auto;
        margin-top: 20px;
        text-align: center;
      }
    }
  </style>
</head>
<body>

<div class="sidebar">
  <h2>Admin Panel</h2>
  <div class="logout">
    <a href="/logout">Logout</a>
  </div>
  <ul>
    <li><a href="#">Dashboard</a></li>
    <li><a href="#">Manage Drivers</a></li>
    <li><a href="#">Manage Customers</a></li>
    <li><a href="#">Manage Bookings</a></li>
    <li><a href="#">View Reports</a></li>
  </ul>
</div>

<div class="content">
  <h2>Welcome, Admin</h2>
  <div class="dashboard-cards">
    <div class="card">
      <h3>Total Bookings</h3>
      <p>120</p>
    </div>
    <div class="card">
      <h3>Active Drivers</h3>
      <p>45</p>
    </div>
    <div class="card">
      <h3>Total Earnings</h3>
      <p>$10,500</p>
    </div>
  </div>
  <div class="admin-actions">
    <button onclick="location.href='/add-driver'">Add Driver</button>
    <button onclick="location.href='/add-customer'">Add Customer</button>
    <button onclick="location.href='/generate-report'">Generate Report</button>
  </div>
</div>

</body>
</html>