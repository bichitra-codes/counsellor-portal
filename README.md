# Counsellor Portal

A web-based application developed using Spring Boot to manage student information, counselling sessions, and student progress efficiently.

## Features

- Counsellor registration and login
- Manage student enquiry information
- Add new student enquiries
- View all student enquiries
- Edit existing enquiry details
- Dashboard for managing counselling activities
- Database integration using MySQL

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Maven
- MySQL
- HTML and CSS

## Project Structure

```text
Counsellor-Portal
├── src
│   ├── main
│   │   ├── java/in/counsellor
│   │   │   ├── controller
│   │   │   ├── dto
│   │   │   ├── entity
│   │   │   ├── repo
│   │   │   ├── service
│   │   │   └── CounsellorPortalApplication.java
│   │   └── resources
│   │       ├── templates
│   │       │   ├── add_enq.html
│   │       │   ├── dashboard.html
│   │       │   ├── edit_enq.html
│   │       │   ├── login_form.html
│   │       │   ├── register_form.html
│   │       │   └── view_enqs.html
│   │       └── application.properties
│   └── test
│       └── java/in/counsellor
└── pom.xml
```

## Prerequisites

Before running the project, make sure you have installed:

- Java JDK 17 or above
- Maven
- MySQL
- Spring Tool Suite or IntelliJ IDEA

## Database Configuration

1. Open MySQL and create a database.

```sql
CREATE DATABASE counsellor_db;
```

2. Open the following file:

```text
src/main/resources/application.properties
```

3. Configure your database connection details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/counsellor_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=9090
```

Replace `YOUR_MYSQL_PASSWORD` with your MySQL password.

## How to Run the Project

### Step 1: Clone the Repository

```bash
git clone https://github.com/bichitra-codes/counsellor-portal.git
```

### Step 2: Open the Project

Open the project in Spring Tool Suite or IntelliJ IDEA.

### Step 3: Configure the Database

Update the MySQL username, password, and database name in `application.properties`.

### Step 4: Build the Project Using Maven

Open the terminal in the project root directory, where `pom.xml` is located.

```bash
mvn clean install
```

### Step 5: Run the Application

```bash
mvn spring-boot:run
```

### Step 6: Open the Application

After the application starts successfully, open your browser:

```text
http://localhost:9090
```

## Run Using Spring Tool Suite

1. Open the project in Spring Tool Suite.
2. Right-click the project.
3. Select **Run As → Spring Boot App**.
4. Wait for the application to start.
5. Open `http://localhost:9090` in your browser.

## Run Using the JAR File

Build the project using Maven:

```bash
mvn clean package
```

Run the generated JAR file:

```bash
java -jar target/counsellor-portal-0.0.1-SNAPSHOT.jar
```

The application will run on port `9090`.

## Application URL

```text
http://localhost:9090
```

## Author

Bichitra Gouda

GitHub: https://github.com/bichitra-codes
