<!DOCTYPE html>
<html>
<head>
    <title>MegaCityCab - Home</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(to bottom right, #e0f2f7, #bbdefb);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .index-container {
            background-color: #fff;
            padding: 3em;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
            border-radius: 8px;
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #0d47a1;
            margin-bottom: 1.5em;
        }

        button {
            background-color: #0d47a1;
            color: white;
            padding: 1em;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 1.1em;
            margin-bottom: 1em;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0b3983;
        }
    </style>
</head>
<body>

<div class="index-container">
    <h2>Welcome to MegaCityCab</h2>
    <button onclick="location.href='/signin?role=admin'">Sign In as Admin</button>
    <button onclick="location.href='/signin?role=driver'">Sign In as Driver</button>
    <button onclick="location.href='/signin?role=customer'">Sign In as Customer</button>
</div>

</body>
</html>