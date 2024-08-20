package com.levi.microservices.client.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientServiceApplicationTests {

    @ServiceConnection
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15.2");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        String requestBody = """
                {
                    "name": "John Doe",
                    "email": "johndoe@example.com",
                    "address": {
                        "street": "123 Main St",
                        "city": "Anytown",
                        "state": "CA",
                        "zip": "12345"
                    }
                }
                """;
    }

    static {
        postgresContainer.start();
    }

    @Test
    void shouldCreateIndividualClient() {
        String requestBody = """
                {
                    "name": "John Doe",
                    "document": "123456789",
                    "mothersName": "Jane Doe",
                    "address": {
                        "street": "Main St",
                        "number": "123",
                        "complement": "Apt 456",
                        "neighborhood": "Downtown",
                        "city": "Metropolis",
                        "state": "MT",
                        "zipCode": "12345-678"
                    }
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/client")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("John Doe"))
                .body("address.street", Matchers.equalTo("Main St"))
                .body("address.city", Matchers.equalTo("Metropolis"))
                .body("address.state", Matchers.equalTo("MT"))
                .body("address.zipCode", Matchers.equalTo("12345-678"));
    }


}
