# Cake Manager

This is a sample spring-boot, mysql and React microservices project. This uses a docker compose file for booting all applications up. Please make sure you have installed docker in your local environment before running this application.

To run this application, in the root directory;

### `docker-compose up -d`

Runs the entire app with docker compose.

### `docker-compose up -d --build`

Rebuilds the docker images when you have code changes.

## Configurations

### Mysql

- MYSQL_DATABASE: cakemanager
- MYSQL_USER: test
- MYSQL_PASSWORD: test123

### Spring Boot backend application

- http://localhost:8080/api/cakes

You can create, find, update and delete a cake event through this application. The data will be stored in the databases. I have implemented an MVC application in this project. It has controller, service and repository layer. I have been also using mapstruct library for DTO conversion. All possible unit tests have been implemented by using JUnit and Mockito libraries.

Important libraries I have used in this project;

- Spring boot JPA
- MySql connector
- Lombok
- Mapstruct
- JUnit and Mockito

### React frontend application

- http://localhost:3000/

Please visit this link to see how the application works on the browser.
