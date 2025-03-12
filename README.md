# Mega City Cab - Online Vehicle Reservation System

## Project Overview
Mega City Cab is an online vehicle reservation system designed to streamline cab booking operations. The system allows customers to register, book rides, manage driver and vehicle details, and calculate fares efficiently.

## Features
- User authentication (Login/Signup)
- Customer booking management
- Booking details retrieval
- Fare calculation and billing
- Driver and vehicle management
- System usage guidelines (Help section)
- Logout and session management

---

## 🚀 Getting Started

### **1. Clone the Repository**
```sh
git clone https://github.com/Dasunlakshitha/megacitycab-ap.git
cd megacitycab-ap
```

### **2. Configure the Database**
- Install **PostgresSQL** and create a database named `megacitycab`
- Update `src/main/resources/application.properties` with your database credentials:
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5433/car-booking-system
  spring.datasource.username=yourusername
  spring.datasource.password=yourpassword
  ```

### **3. Build & Run the Application**
#### **Using Maven**
```sh
mvn spring-boot:run
```
#### **Using IntelliJ IDEA**
1. Open the project in **IntelliJ IDEA**.
2. Select **Run → Edit Configurations**.
3. Set the main class as `com.megacitycab.MegacityCabApplication`.
4. Click **Run**.

---

## 🐟 API Endpoints

### **Authentication & Users**
| Endpoint                   | Method | Description                  |
|----------------------------|--------|------------------------------|
| `/api/users`               | POST   | Create or update a user      |
| `/api/users/{id}`          | GET    | Get a user by ID             |
| `/api/users`               | GET    | Get all users                |
| `/api/users/{id}`          | DELETE | Delete a user by ID          |
| `/api/users/login`         | POST   | User login                   |
| `/api/users/{id}`          | PUT    | Update user details          |

### **Admins**
| Endpoint                   | Method | Description                  |
|----------------------------|--------|------------------------------|
| `/api/admins`              | POST   | Create or update an admin    |
| `/api/admins/{id}`         | GET    | Get an admin by ID           |
| `/api/admins`              | GET    | Get all admins               |
| `/api/admins/{id}`         | DELETE | Delete an admin by ID        |

### **Customers**
| Endpoint                   | Method | Description                  |
|----------------------------|--------|------------------------------|
| `/api/customers`           | POST   | Create or update a customer  |
| `/api/customers/{id}`      | GET    | Get a customer by ID         |
| `/api/customers`           | GET    | Get all customers            |
| `/api/customers/{id}`      | DELETE | Delete a customer by ID      |
| `/api/customers/{id}`      | PUT    | Update a customer            |

### **Drivers**
| Endpoint                   | Method | Description                  |
|----------------------------|--------|------------------------------|
| `/api/drivers`             | POST   | Create or update a driver    |
| `/api/drivers/{id}`        | GET    | Get a driver by ID           |
| `/api/drivers`             | GET    | Get all drivers              |
| `/api/drivers/{id}`        | DELETE | Delete a driver by ID        |
| `/api/drivers/{id}`        | PUT    | Update a driver              |

### **Bookings**
| Endpoint                   | Method | Description                     |
|----------------------------|--------|---------------------------------|
| `/api/bookings`            | POST   | Create or update a booking      |
| `/api/bookings/{id}`       | GET    | Get a booking by ID            |
| `/api/bookings`            | GET    | Get all bookings               |
| `/api/bookings/{id}`       | DELETE | Delete a booking by ID         |
| `/api/bookings/{id}`       | PUT    | Update a booking               |
| `/api/bookings/generate-pdf` | GET  | Generate a PDF report of bookings |

### **Payments**
| Endpoint                   | Method | Description                   |
|----------------------------|--------|-------------------------------|
| `/api/payments`            | POST   | Create or update a payment    |
| `/api/payments/{id}`       | GET    | Get a payment by ID           |
| `/api/payments`            | GET    | Get all payments              |
| `/api/payments/{id}`       | DELETE | Delete a payment by ID        |
| `/api/payments/{id}`       | PUT    | Update a payment              |

### **Vehicles**
| Endpoint                   | Method | Description                   |
|----------------------------|--------|-------------------------------|
| `/api/vehicles`            | POST   | Create or update a vehicle    |
| `/api/vehicles/{id}`       | GET    | Get a vehicle by ID           |
| `/api/vehicles`            | GET    | Get all vehicles              |
| `/api/vehicles/{id}`       | DELETE | Delete a vehicle by ID        |
| `/api/vehicles/{id}`       | PUT    | Update a vehicle              |


---

## 🛠 Technologies Used
- **Java 19**  
- **Spring Boot**  
- **PostgresSQL** (Database)  
- **JSP** (Frontend)  
- **Bootstrap** (UI Enhancements)  
- **Maven** (Build Tool)  
- **GitHub** (Version Control)  

---

## 🔄 Version Control Workflow
- The **master** branch is the default/main branch.
- Feature branches are created for specific tasks and merged into the **development** branch.
- After testing, the development branch is merged into **master**.

---

## 🏰 Deployment
1. Build the project:
   ```sh
   mvn clean package
   ```
2. Run the `.jar` file:
   ```sh
   java -jar target/megacitycab-0.0.1-SNAPSHOT.jar
   ```
3. Expose the local server using **ngrok**:
   ```sh
   ngrok http 8080
   ```

---

## 📝 License
This project is licensed under the **MIT License**.

---

## 🤝 Contributing
Pull requests are welcome! Follow the feature-branch workflow and submit a PR.

---

## 📧 Contact
For support, contact: [dasunlakshitha696@gmail.com]  
GitHub Repository: [megacitycab-ap](https://github.com/Dasunlakshitha/megacitycab-ap)
