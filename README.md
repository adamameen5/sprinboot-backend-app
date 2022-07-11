# Spring Boot Application
For this application, you are expected to build an API backend with Spring Boot framework for an imaginary mobile application. Please read the constraints and tasks carefully and follow submission guideline at the bottom of the page.

## Scenario
CryptoTracker is a cryptocurrency tracker app which allows its users to create alerts to be notified when a price of a coin reaches the price user determines.

User can create multiple alerts and can track the alert status anytime (triggered or waiting).

There is also currency list page where all coins with their current prices are listed.

The admin user also manages the currencies that will be listed on the app.

## Tech stack
- [Spring Boot](https://spring.io/projects/spring-boot)
- Java 8+ or Kotlin
- Gradle or Maven
- Spring Data JPA
- Hibernate
- MySQL or PostgreSQL
- GIT for version control

## General Application Constraints
- Users are using CryptoTracker mobile app, assume that the API is only consumed by mobile
- Data should only be accepted from the registered users with their ownership rights.
- There are two types of users: Admin and User.
    - Both user types can create alerts.
    - Both users can query currencies.
    - Only admin can manage the currency types in the system.
- You don't have to implement a registration flow, you can add predefined users to database and use them in this project.

## Tasks
1. Implement API endpoints for managing the CRUD operations for the Currency types
    - Currency Entity: Some properties may be `name`, `symbol`, `currentPrice`, `createdTime`, `enabled` etc. Decide on your own if you need more.
    - If a request arrives to create one of the coins below then `UnsupportedCurrencyCreationException` should be thrown. Unsupported coins: [ ETH, LTC, ZKN, MRD, LPR, GBZ ]
    - Admin user can add/remove currencies
    - All users can query currencies
2. Implement API endpoints for maintaining the CRUD operations for alerts.
    - Alert Entity: `currency`, `targetPrice`, `createdAt`, `status(NEW, TRIGGERED, ACKED, CANCELLED)`
    - The status of the alert
        - NEW if the price is not in the target price
        - TRIGGERRED if the pice is reached
        - ACKED if the user closes the alert
        - CANCELLED if the user cancels the alert
    - User can create/edit/delete the alerts
    - User can cancel the alert if it is not triggered yet
    - User can acknowledge the alert when he is notified.(The target price was reached)
3. Create a ScheduleTask that checks the alerts and notifies the users if the target price is reached
    - For the notification part you can write a simple log on console. Email or push notification is not considered here.
    - ScheduledTask should run every 30 seconds. 
    - Note that, we will change the current price information manually on database while testing.

## Tasks
1. Test coverage of 60%. Please do not try to increase more.
2. Add Docker support for your application. It should be buildable with Docker and can be run with `docker run`
3. If you are using Docker, implement a `docker-compose` stack that contains the database and your application.
4. Add your design decisions to `DOC.md` file.
5. Create a Postman collection in order to test the endpoints. Export and add it to this repository.
