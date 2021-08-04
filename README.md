# Cake Manager

This is a sample spring-boot and mysql project. It uses a docker compose file. Please make sure you have installed docker in your local environment before run this application.

To run this application, in the root directory;

### `docker-compose up`

Runs the entire app with docker compose.

### `docker-compose up --build`

Rebuilds the docker images when you have code changes.

## Configurations

### Mysql

- MYSQL_DATABASE: cakemanager
- MYSQL_USER: test
- MYSQL_PASSWORD: test123

### Spring Boot Application

- http://localhost:8080/api/cakes

You can create, find and update a cake event through this application. The data will be stored in the databases.
