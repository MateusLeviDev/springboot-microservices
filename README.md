# Microservices Documentation

![Last Commit](https://img.shields.io/github/last-commit/MateusLeviDev/lev-microservices)

## Screenshots

- ![Screenshot 1](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/4f3ea629-3a22-43d3-b429-aa6d4c2f59d1)
- ![Screenshot 2](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/facfd513-61c0-47e2-902b-46529f63e709)

## Components

### Testcontainers

`Testcontainers` is a testing library that provides easy and lightweight APIs for bootstrapping integration tests with real services wrapped in Docker containers. It allows running tests in isolated environments, testing the application with the same services that will be used in production. It offers simplified configuration and automatic cleanup. Testcontainers can be executed in various environments, including local development machines, continuous integration (CI) servers, and cloud environments, as long as they support Docker.

### Keycloak

`Keycloak` is a tool created by Red Hat for managing user credentials and permissions. It can serve as the official repository of users for a company, either through registering all users or linking with a database. Keycloak supports concepts like Identity Providers, which are external identity providers such as Google, Facebook, or LDAP, and User Federation, which allows connecting external databases with Keycloak.

### Eureka

`Eureka` acts as a centralized registration server, where each microservice registers itself with the server by providing its location (IP address and port). The architecture based on Eureka follows a client-server model, where each client service registers with the Eureka server when started and deregisters when terminated. Eureka maintains an up-to-date registry of all registered services and provides a query interface for clients to discover available services. It also offers features for fault tolerance and load balancing.

### MongoDB and MySQL

The choice of database for each service is usually based on various factors, including business requirements, performance needs, scalability, and development team preferences.

- `MongoDB`: Flexible Data Modeling, Ideal for scenarios where data can have variable or semi-structured structures. Horizontal Scalability: Highly scalable and easily supports data distribution across multiple nodes.
  
- `MySQL`: Data Consistency and Integrity, Relationships and Complex Queries.

## Communication Patterns

### Asynchronous Communication

Asynchronous communication is suitable when the ordering service does not need an immediate response from the notification service to complete the operation. Instead, it simply sends a notification message to the notification service and continues with its operation.

Benefits:
- Decoupling: Weaker coupling between services as the ordering service does not need to wait for the notification service's response.
- Resilience: If the notification service is temporarily unavailable, the ordering service can continue working normally and attempt to send the notification message later without interruption.
- Scalability: Since the ordering service is not waiting for the notification service's response, it can process orders in parallel and scale horizontally more efficiently.

### Synchronous Communication

Synchronous communication is suitable when the ordering service requires an immediate response from the inventory service to complete the order operation. For example, the ordering service may need to check for sufficient inventory before confirming an order.
