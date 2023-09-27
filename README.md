# Research Laboratory Management Application

![Project Logo](![_84d118e5-180e-4713-affe-62eb84b4398f-removebg-preview](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/3f9af658-8ec8-455f-b0e3-69c6c61a5fba))

## Project Description

The Research Laboratory Management Application is a web-based platform designed to streamline the management of research projects, laboratory members, publications, and resources within a laboratory environment. It promotes collaboration and communication among laboratory stakeholders.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Key Features and Components](#key-features-and-components)
- [Getting Started](#getting-started)
- [User Roles and Permissions](#user-roles-and-permissions)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java
- Spring Framework (Spring Boot, Spring MVC, Spring Data JPA)
- Hibernate
- MySQL (Database)
- Thymeleaf (Template engine)

## Showcase

### Login Page
![sign in](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/d78c4e2a-1830-415d-bce5-24df3d5de54b)

### Sign up page
![signup](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/235716cf-ddb6-4d24-ab33-dfdfd434dfcd)

### Adding Members
![adding members](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/c565e11e-1ebd-4444-a375-7c965986551c)

### Adding Projects
![add project](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/034f7801-5184-4a0b-8c78-acf0be3c199d)

### Adding Publications
![add publication](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/b8921cc3-1dca-44c6-813b-41a076115f70)

### Adding Resources
![edit resource](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/15d13ef9-8b32-4386-9d65-142e2521155a)

### Admin Dashboard
![admin dashboard](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/d0fdcf93-c80e-4008-b8c2-746d0360743e)

### Projects And Publications View
![publication](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/3c53989c-7c2f-4680-8c4b-d5ab037a0b86)

### Resources View
![resource](https://github.com/GoldenDovah/spring-lab-management/assets/19519174/1dd7d5ec-a864-422f-a1e5-23c3bddae1d6)


## Project Structure

src/main/java:
├── com.example.labmanagement: Root package.
├── controller: Spring MVC controllers to handle web requests.
├── model: Hibernate feature classes representing tables in the database.
├── repository: Spring Data JPA repository interfaces for database operations.
├── service: Business logic and services.
├── security: Security configurations for user authentication.
├── web: Configurations specific to the web application.
├── LabManagementApplication.java: The main class of the application.

src/main/resources:
├── application.properties: Configuring database connection, Spring Boot settings, and more.
├── static: Static resources such as CSS, JavaScript, and images.
├── templates: Thymeleaf templates for dynamic views.


**Database Schema:**

- `project`: Stores information about research projects (id, title, description, start date, end date, etc.).
- `users`: Stores information about lab members (id, last name, first name, email, etc.).
- `publication`: Stores information about publications (id, title, author, publication date, etc.).
- `resource`: Stores information about lab resources (id, name, description, availability, etc.).

## Key Features and Components

### Project Management

- Allows you to create, edit, and delete research projects.
- Displays the list of projects with their details.

### Member Management

- Add, edit, and delete lab members.
- Displays a list of members with their information.

### Publication Management

- Allows you to add and view project-related publications.
- Displays a list of publications with their details.

### Resource Management

- Add and manage laboratory resources (hardware, equipment, etc.).
- Displays a list of available resources.

### Project Dashboard

- Displays an overview of current projects, members, and recent publications.

### Thymeleaf Templates

- Uses Thymeleaf templates to render dynamic HTML pages.
- Creates templates for management views of projects, members, publications, etc.

### User Management and Security

- Uses Spring Security for authentication and role management.
- Manages administrator and member roles.

### Management Service

- Implements services for CRUD (create, read, update, delete) operations on projects, members, posts, and more.

### Controllers

- Uses Spring MVC controllers to handle HTTP requests and display corresponding views.

### Configure Security

- Configures Spring Security to manage authentication and authorizations.
- Restricts access to certain pages to logged-in users with specific roles.

### Application Entry Point

The `LabManagementApplication` class starts the Spring Boot application.

## Getting Started

To run this project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/goldendovah/research-lab-management.git`
2. Configure the database connection in `application.properties`.
3. Build and run the project using your preferred Java IDE.

## User Roles and Permissions

This project supports the following user roles:

1. **Administrator:**
   - Access to an administrative dashboard.
   - Manage lab members (add, edit, delete).
   - Manage research projects (add, modify, delete).
   - Manage publications (add, edit, delete).
   - Manage lab resources (add, edit, delete).
   - Access to role and permission management (add, modify, delete).

2. **Laboratory Director:**
   - Access to a director dashboard.
   - See the list of members of the laboratory.
   - See details of research projects.
   - See publications associated with the lab.

3. **Teacher:**
   - Access to a teacher dashboard.
   - See the list of research projects.
   - See details of research projects.
   - Add project-related posts.

4. **PhD Student:**
   - Access to a student dashboard.
   - See the list of research projects.
   - See details of research projects.
   - View publications related to projects.


## Acknowledgments

I would like to extend my sincere appreciation to the creator of the Bootstrap template that I used in this project. The visually appealing and responsive design elements provided by this template have greatly enhanced the user experience of this application.

**SB Admin 2:** [SB Admin 2]((https://startbootstrap.com/theme/sb-admin-2))
