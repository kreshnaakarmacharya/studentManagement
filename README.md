# Student Management System

A web-based **Student Management System** developed using **Java Spring Boot, Spring Data JPA, MySQL, Thymeleaf, HTML, CSS, and Bootstrap**.

The system helps manage students, teachers, courses, and subjects through a simple web interface. It also includes login and role-based access functionality for different users.

## 🚀 Features

### 👨‍🎓 Student Management

* Add new students
* View all students
* View individual student details
* Edit student information
* Delete students
* Manage student profiles
* Assign students to courses

### 👨‍🏫 Teacher Management

* Add teachers
* View all teachers
* View teacher details
* Edit teacher information
* Delete teachers
* Manage teacher profiles
* View subjects assigned to teachers

### 📚 Course Management

* Add courses
* View all courses
* View course details
* Edit courses
* Delete courses
* Manage students associated with courses

### 📖 Subject Management

* Add subjects
* View all subjects
* Edit subjects
* Delete subjects
* Assign subjects to teachers
* View subjects assigned to teachers

### 🔐 Authentication & Security

* User login
* User authentication
* Role-based access
* Protected application pages
* Spring Security integration

### 🛠️ Admin Dashboard

* Admin dashboard
* Manage students
* Manage teachers
* Manage courses
* Manage subjects

---

## 🧑‍💻 Technologies Used

| Technology      | Purpose                          |
| --------------- | -------------------------------- |
| Java            | Backend programming              |
| Spring Boot     | Application framework            |
| Spring MVC      | Web application development      |
| Spring Data JPA | Database operations              |
| Spring Security | Authentication and authorization |
| MySQL           | Database                         |
| Thymeleaf       | Server-side HTML rendering       |
| HTML5           | Frontend structure               |
| CSS3            | Styling                          |
| Bootstrap       | Responsive UI                    |
| Maven           | Dependency management            |
| Git & GitHub    | Version control                  |

---

## 📂 Project Structure

```text
studentManagement/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/std/student/
│   │   │       │
│   │   │       ├── config/
│   │   │       │   ├── DataInitilizer.java
│   │   │       │   └── SecurityConfig.java
│   │   │       │
│   │   │       ├── controllers/
│   │   │       │   ├── AdminController.java
│   │   │       │   ├── CourseController.java
│   │   │       │   ├── HomeController.java
│   │   │       │   ├── StudentController.java
│   │   │       │   ├── SubjectController.java
│   │   │       │   └── TeacherController.java
│   │   │       │
│   │   │       ├── DTO/
│   │   │       │   ├── AddStudentDTO.java
│   │   │       │   └── AddTeacherDTO.java
│   │   │       │
│   │   │       ├── models/
│   │   │       │   ├── Course.java
│   │   │       │   ├── Student.java
│   │   │       │   ├── Subject.java
│   │   │       │   ├── Teacher.java
│   │   │       │   ├── User.java
│   │   │       │   ├── POJO/
│   │   │       │   │   └── Role.java
│   │   │       │   └── Security/
│   │   │       │       └── CustomUserDetails.java
│   │   │       │
│   │   │       ├── repositories/
│   │   │       │   ├── CourseRepository.java
│   │   │       │   ├── StudentRepo.java
│   │   │       │   ├── SubjectRepository.java
│   │   │       │   ├── TeacherRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       │
│   │   │       └── services/
│   │   │           ├── CourseService.java
│   │   │           ├── StudentService.java
│   │   │           ├── SubjectService.java
│   │   │           ├── TeacherService.java
│   │   │           └── Security/
│   │   │               └── CustomUserDetailsService.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   └── CSS/
│   │       │
│   │       ├── templates/
│   │       │   ├── Admin/
│   │       │   ├── Courses/
│   │       │   ├── HomePage/
│   │       │   ├── Student/
│   │       │   ├── Subjects/
│   │       │   ├── Teacher/
│   │       │   └── fragments/
│   │       │
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
└── README.md
```

---

## 🗄️ Database

This project uses **MySQL** as the database.

Create a database before running the application:

```sql
CREATE DATABASE student_management;
```

Update your `application.properties` with your database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> Replace `YOUR_PASSWORD` with your local MySQL password.

---

## ⚙️ Requirements

Before running the project, make sure you have:

* Java JDK 21 or later
* Maven
* MySQL Server
* MySQL Workbench (optional)
* IntelliJ IDEA / STS / Eclipse
* Git

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/kreshnaakarmacharya/studentManagement.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA, STS, or Eclipse**.

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE student_management;
```

Then configure your database credentials in:

```text
src/main/resources/application.properties
```

### 4. Install dependencies

Using Maven:

```bash
mvn clean install
```

### 5. Run the application

You can run the Spring Boot application from your IDE or use:

```bash
mvn spring-boot:run
```

### 6. Open in browser

After starting the application, open:

```text
http://localhost:8080
```

---

## 🔐 Application Roles

The application supports different roles for controlling access.

### Admin

Admin can manage:

* Students
* Teachers
* Courses
* Subjects

### Teacher

Teacher can access teacher-related functionality such as:

* Teacher dashboard
* Profile
* Assigned subjects

### Student

Student can access student-related functionality such as:

* Student dashboard
* Profile
* Course information

---

## 🔄 Application Flow

```text
                    ┌───────────────┐
                    │     Login     │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │ Authentication│
                    └───────┬───────┘
                            │
              ┌─────────────┼─────────────┐
              ▼             ▼             ▼
          ┌───────┐     ┌────────┐    ┌─────────┐
          │ Admin │     │Teacher │    │ Student │
          └───┬───┘     └───┬────┘    └────┬────┘
              │              │              │
              ▼              ▼              ▼
        Manage Users     Subjects       Student
        Courses           Profile       Profile
        Subjects
        Students
        Teachers
```

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Controller Layer

Handles HTTP requests and responses.

Example:

```text
StudentController
TeacherController
CourseController
SubjectController
```

### Service Layer

Contains the application's business logic.

Example:

```text
StudentService
TeacherService
CourseService
SubjectService
```

### Repository Layer

Handles database operations using Spring Data JPA.

Example:

```text
StudentRepo
TeacherRepository
CourseRepository
SubjectRepository
UserRepository
```

### Model Layer

Represents database entities.

Example:

```text
Student
Teacher
Course
Subject
User
```

---

## 🔒 Security

Spring Security is used to provide authentication and authorization.

Important security classes include:

```text
SecurityConfig
CustomUserDetails
CustomUserDetailsService
User
Role
```

The system uses role-based access to prevent unauthorized users from accessing restricted functionality.

---

## 📱 User Interface

The frontend is built using:

* HTML5
* CSS3
* Bootstrap
* Thymeleaf

Reusable Thymeleaf fragments are used for common components such as:

```text
header.html
sidebar.html
footer.html
```

---

## 🧪 Testing

The application can be tested by checking:

* Login functionality
* Student CRUD operations
* Teacher CRUD operations
* Course CRUD operations
* Subject CRUD operations
* Role-based access
* Form validation
* Navigation
* Database operations
* Unauthorized access handling

---

## 🚀 Future Improvements

Possible future enhancements include:

* REST API
* Pagination
* Advanced search and filtering
* Email notifications
* Student attendance management
* Examination and marks management
* Fee/payment management
* File/image upload
* Password reset
* Improved role and permission management
* Automated unit and integration testing
* Deployment to a cloud server

---

## 👨‍💻 Author

**Krishnaa Karmacharya**

Bachelor in Information Management (BIM)

---

## 📄 License

This project is created for **educational and learning purposes**.
