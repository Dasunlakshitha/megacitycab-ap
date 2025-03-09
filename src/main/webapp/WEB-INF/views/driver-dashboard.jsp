<!DOCTYPE html>
<html>
<head>
  <title>Driver Dashboard - MegaCityCab</title>
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
      min-height: 100vh; /* Ensure full viewport height */
    }

    .sidebar {
      width: 100%; /* Full width on smaller screens */
      background-color: #0d47a1;
      padding: 20px;
      color: white;
      position: relative; /* For absolute positioning of logout */
      height: auto;
      text-align: center;
    }

    .sidebar h2 {
      margin-bottom: 20px;
    }

    .sidebar ul {
      list-style: none;
      padding: 0;
      display: flex; /* Horizontal menu */
      flex-wrap: wrap; /* Allow wrapping */
      justify-content: center; /* Center menu items */
    }

    .sidebar ul li {
      margin: 10px;
    }

    .sidebar ul li a {
      text-decoration: none;
      color: white;
      display: block;
      padding: 10px 15px;
      border-radius: 5px;
      transition: background 0.3s;
    }

    .sidebar ul li a:hover {
      background-color: #0b3983;
    }

    .content {
      padding: 30px;
      width: 100%;
      box-sizing: border-box;
      flex-grow: 1; /* Allow content to expand */
    }

    .dashboard-cards {
      display: flex;
      flex-wrap: wrap; /* Allow cards to wrap */
      gap: 30px;
      justify-content: center; /* Center cards */
    }

    .card {
      background-color: white;
      padding: 25px;
      border-radius: 8px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
      flex: 1 1 350px; /* Adjust flex-basis as needed */
      text-align: center;
    }

    .card h3 {
      margin-bottom: 15px;
    }

    .logout {
      position: absolute;
      top: 20px;
      right: 20px;
      width: auto;
      text-align: right;
      margin: 0;
    }

    .logout a {
      color: white;
      text-decoration: none;
      display: block;
      background-color: #ff3d00;
      padding: 10px 15px;
      border-radius: 5px;
      transition: background 0.3s;
      font-size: 16px;
    }

    .driver-actions {
      margin-top: 30px;
      display: flex;
      flex-wrap: wrap; /* Allow buttons to wrap */
      gap: 20px;
      justify-content: center; /* Center buttons */
    }

    .driver-actions button {
      background-color: #0d47a1;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background 0.3s;
      font-size: 16px;
    }

    .driver-actions button:hover {
      background-color: #0b3983;
    }

    @media (max-width: 768px) {
      .sidebar ul {
        flex-direction: column; /* Vertical menu on small screens */
      }

      .card {
        flex: 1 1 100%; /* Full width cards */
      }

      .logout {
        position: relative; /* Reset positioning */
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
  <h2>Driver Panel</h2>
  <div class="logout">
    <a href="/logout">Logout</a>
  </div>
  <ul>
    <li><a href="#">Dashboard</a></li>
    <li><a href="#">View Assigned Rides</a></li>
    <li><a href="#">Update Ride Status</a></li>
    <li><a href="#">Earnings Report</a></li>
  </ul>
</div>

<div class="content">
  <h2>Welcome, Driver</h2>
  <div class="dashboard-cards">
    <div class="card">
      <h3>Today's Rides</h3>
      <p>5</p>
    </div>
    <div class="card">
      <h3>Completed Rides</h3>
      <p>20</p>
    </div>
    <div class="card">
      <h3>Total Earnings</h3>
      <p>$2,500</p>
    </div>
  </div>

  <div class="driver-actions">
    <button onclick="location.href='/view-rides'">View Assigned Rides</button>
    <button onclick="location.href='/update-ride-status'">Update Ride Status</button>
    <button onclick="location.href='/earnings'">View Earnings</button>
  </div>
</div>

</body>
</html>