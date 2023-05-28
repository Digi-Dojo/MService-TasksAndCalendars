# MService-TasksAndCalendars

This repository contains the backend microservice for managing tasks and calendar events in the Dig-dojo project.

## Table of Contents

- [Architecture](#architecture)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Testing](#testing)
- [Contributing](#contributing)

## Architecture

The MService-TasksAndCalendars microservice is built using Java and Spring Boot. It follows a standard Spring Boot project structure.

## Requirements

- Java 17 or higher
- Gradle
- MariaDB

## Getting Started

1. Clone the repository:
git clone https://github.com/your-organization/MService-TasksAndCalendars.git
    cd MService-TasksAndCalendars

2. Configure your database connection in `src/main/resources/application.properties`.

3. Build the project:
   gradle build
4. Run the application:
   gradle bootRun

## Testing

To run tests, execute the following command in the project root:
gradle test

This will run all test classes in the `src/test/java` directory.
















