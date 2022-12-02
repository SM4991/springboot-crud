# Spring boot CRUD Boilerplate

## Introduction
This is a web application boilerplate that provides a quick and easy setup for creating RESTful APIs with a database backend. It includes a basic CRUD (create, read, update, delete) application structure, as well as support for database migrations. 

The boilerplate is designed to be used as a starting point for developing modern web applications quickly and easily, allowing for rapid prototyping and development. It is also designed to be extensible and customizable, as the user can add their own custom features and functionality.

Swagger UI is implemented to test the rest apis.

## Demo
![demo](src/main/resources/static/images/demo.gif)

## Tech Stack
* Java
* Maven
* Spring boot
* Postgresql
* Swagger UI
* Thymelyf
* Docker

## Installation
This project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies and run it.

## Database configuration
1. Create a Postgresql database with the name Eg. `springbootdb`.
2. Add the credentials to below environment variables.
```
POSTGRES_PASSWORD=password
POSTGRES_USERNAME=postgres
POSTGRES_DB_NAME=springbootdb
POSTGRES_DB_HOST=localhost
POSTGRES_DB_PORT=5432
```
3. Or add the credentials to application properties.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/springbootdb
spring.datasource.username=postgres
spring.datasource.password=password
```

## Usage
Run the project through the IDE and head out to [http://localhost:8080](http://localhost:8080)

or

run this command in the command line:
```
mvn spring-boot:run
```

## Postman Api Collection 
[Api Collection](https://documenter.getpostman.com/view/24715336/2s8Yt1sV6s)

## Gitpod Workspace
We have added support to run this on gitpod directly to check how this application works.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/SM4991/springboot-crud/blob/master/install.gitpod.sh)

#### Steps to do
1. Click on the open in gitpod button, it will open a gitpod workspace. The services will start running automatically.
2. Once the services has started. Go to ports tab and click on the link with 8080 port.
3. This is the spring boot application url. Eg. **https://8080-sm4991-springbootcrud-k0gqb8u14ub.ws-us77.gitpod.io/**.
4. To test the apis using swagger ui, click on the link given on homepage.
5. To test the apis using postman collection, click on the link given on homepage.
