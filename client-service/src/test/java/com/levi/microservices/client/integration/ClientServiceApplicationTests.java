package com.levi.microservices.client.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.web.JsonPath;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientServiceApplicationTests {

    @ServiceConnection
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15.2");

    @LocalServerPort
    private Integer port;

    private String requestBody;
    private String responseBody;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        requestBody = """
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

        responseBody = """
                {
                	"code": 404,
                	"message": "Entity not found",
                	"details": "Unable to find customer with id: 10"
                }
                """;
    }

    static {
        postgresContainer.start();
    }

    @Test
    void shouldCreateIndividualClient() {

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

    @Test
    void shouldFindClientByIdSuccessfully() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/client")
                .then()
                .statusCode(201);

        RestAssured.given()
                .when()
                .get("/api/client/1")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("John Doe"))
                .body("address.street", Matchers.equalTo("Main St"))
                .body("address.city", Matchers.equalTo("Metropolis"))
                .body("address.state", Matchers.equalTo("MT"))
                .body("address.zipCode", Matchers.equalTo("12345-678"));
    }

    @Test
    void shouldReturn404WhenClientNotFound() {
        long nonExistentClientId = 10L;

        RestAssured.given()
                .when()
                .get("/api/client/" + nonExistentClientId)
                .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", equalTo("Entity not found"))
                .body("details", equalTo("Unable to find customer with id: " + nonExistentClientId));

    }


}
