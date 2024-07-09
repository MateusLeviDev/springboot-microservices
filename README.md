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

- ![Screenshot from 2024-04-13 17-10-27](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/ae421bd9-ae46-4ee8-8b4e-f96e0179b263)


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
- Estabelencendo o processo de Inter Process Communication usando o `Spring Cloud OpenFeign`. Nesse contexto vamos estabelecer uma conexão entre o Order Service e o inventory service to check if the product is in stock or not. Basicamente será enviado uma request e aguarderemos uma response, com base no que será implementado no código (Order Service)
[Spring Cloud OpenFeign Documentation](https://spring.io/projects/spring-cloud-openfeign)
- declarative form of writing Java HTTP clients. openFeign acts like a wrapper.

- ![image](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/158d2912-1a43-4094-91cb-b9a3bca7bdea)

- poderia ser usado Mockito e WireMock na realização dos testes. usei WM, por que, basicamente, usando mockito não estaremos realizando uma chamada HTTP mas sim um mock. No WM mockamos a API itself e, dessa maneira, poderei testar HTTP interaction

### Gateway

spring boot cloud gateway mvc as the api gateway library. If you want to use a 3rd party library you can use the tools like Kong or Nginx, if you want to have more control over the way things in the API Gateway layer then you can implement this using Spring Cloud Gateway

- acts as an entry point for all the requests into our system
- forward the request to the downstream microservice
- Proxy reverso: para o solicitante, as respostas das requisições se apresentarão como do próprio serviço; no entanto, o API Gateway é que estará se passando de serviço realizando os redirecionamentos, tanto de requisição como de resposta.
- é uma maneira de desacoplar a interface do cliente da sua implementação de back-end.
- forward the request to the downstream microservice
- sistemas distribuidos e arquiteturas de microserviço
- estou usando um functional endpoint proggraming model: Spring WebFlux includes WebFlux.fn, a lightweight functional programming model in which functions are used to route and handle requests and contracts are designed for immutability. It is an alternative to the annotation-based programming model but otherwise runs on the same Reactive Core foundation. ou seja, com isso podemos usar os mesmo endpoints por um modelo de programação diferente
- RequestPredicates impl various useful requests matching operations, such as matching based on path, HTTP method, etc.

### Circuit Breaker

implementing circuit breaker pattern using libraries like Resilience 4J and spring cloud circuit beaker.

a general best practice which is used in the real world distributed systems whenever our application are calling some remote services or remote APIs. funciona como um disjuntor que monitora a comunicação entre os serviços e interrompe temporariamente a comunicação com um serviço que está com problemas. is a resilience pattern. spring cloud circuit breaker to impl the resilience pattern, is like a lib that help us to integrate this 4J and the circuit breaker. provides us a abstraction


- Open: This states indicates that the Circuit Breaker is open, and all the traffic going through the Circuit Breaker will be blocked.
- Half-Open: In this state, the Circuit Breaker will start allowing gradually the traffic to the remote service R
- Closed: In this state, the Circuit Breaker will allow all the requests to the service, which means that the service R is working well without any problems.

- ![Screenshot from 2024-06-24 20-27-52](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/01317a23-2672-4a73-843b-78bae7de4c6f)

- ![Screenshot from 2024-06-24 20-28-01](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/e6d6c659-66c3-49a5-84c8-81ab921773ba)

- siginifica que o nenhum dos circuit breakers foram registrados (por que ainda nao foi feita nenhuma request)
- após fazer a request e receber um 503 Service Unavailable, please try again later, verificando novamente o endpoint do actuator health

- ![Screenshot from 2024-06-24 20-31-46](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/bdfecc76-49b4-4d99-8a78-e2496ebd5483)

- conforme configurado nas properties. foi feito um numero mínimo de 5 calls, após falhar tudo ela vai pro estado de half closed

- ![Screenshot from 2024-06-24 20-35-30](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/fd0cfe03-f030-4c7b-a52a-8ca9fd376b78)

- após uma request bem sucedida

- ![Screenshot from 2024-06-24 20-36-15](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/fe26e4b7-6eaf-4cb5-87a3-5e4ee72601cc)


To test the Circuit Breaker in the API Gateway, make sure that one of the services like Product, Order or Inventory Service is unavailable, and then call the corresponding service.
You should see an error – Service Unavailable, please try again later with the status HTTP_503
You can try the same thing also for the Order Service project, by stopping the Inventory Service.
To test the Timeout and Retry, we can introduce a slight delay by adding something like Thread.sleep() to simulate latency for our requests and you can observe that Circuit Breaker will be activated also in these cases.

#### Schema registry
O uso de Schema Registry e Avro em uma aplicação Kafka não apenas melhora a eficiência e o desempenho, mas também simplifica a gestão e a evolução dos dados, proporcionando uma base mais robusta e flexível para o desenvolvimento de sistemas distribuídos.
