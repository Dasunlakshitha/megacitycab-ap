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
- Install **MySQL** and create a database named `megacitycab`
- Update `src/main/resources/application.properties` with your database credentials:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/megacitycab
  spring.datasource.username=root
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
| Endpoint                  | Method | Description                   |
|---------------------------|--------|-------------------------------|
| `/auth/signup`            | POST   | Register a new user           |
| `/auth/login`             | POST   | User login                    |
| `/bookings`               | GET    | Retrieve all bookings         |
| `/bookings/{id}`          | GET    | Retrieve a booking by ID      |
| `/bookings`               | POST   | Create a new booking          |
| `/bookings/{id}`          | PUT    | Update a booking              |
| `/bookings/{id}`          | DELETE | Delete a booking              |

---

## 🛠 Technologies Used
- **Java 19**  
- **Spring Boot**  
- **MySQL** (Database)  
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
For support, contact: [Your Email]  
GitHub Repository: [megacitycab-ap](https://github.com/Dasunlakshitha/megacitycab-ap)
