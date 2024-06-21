package com.levi.microservices.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @LocalServerPort
    private Integer port;

    private String productId;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        String requestBody = """
                {
                	"name": "RTX4060",
                	"description": "GPU",
                	"price": 2000
                }
                """;

        productId = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("RTX4060"))
                .body("description", Matchers.equalTo("GPU"))
                .body("price", Matchers.equalTo(2000))
                .extract().path("id");
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
                {
                	"name": "RTX4080",
                	"description": "GPU",
                	"price": 3000
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("RTX4080"))
                .body("description", Matchers.equalTo("GPU"))
                .body("price", Matchers.equalTo(3000));
    }

    @Test
    void shouldGetAllProducts() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/product/" + productId)
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(productId))
                .body("name", Matchers.equalTo("RTX4060"))
                .body("description", Matchers.equalTo("GPU"))
                .body("price", Matchers.equalTo(2000));
    }

    @Test
    void shouldUpdateProduct() {
        String updateRequestBody = """
                {
                	"name": "RTX4070",
                	"description": "Updated GPU",
                	"price": 2500
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(updateRequestBody)
                .when()
                .put("/api/product/" + productId)
                .then()
                .statusCode(204);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/product/" + productId)
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(productId))
                .body("name", Matchers.equalTo("RTX4070"))
                .body("description", Matchers.equalTo("Updated GPU"))
                .body("price", Matchers.equalTo(2500));
    }

    @Test
    void shouldDeleteProduct() {
        // Create a new product to delete
        String requestBody = """
                {
                	"name": "RTX4090",
                	"description": "GPU",
                	"price": 3500
                }
                """;

        String deleteProductId = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Delete the product
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/product/" + deleteProductId)
                .then()
                .statusCode(204);

        // Verify deletion
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/product/" + deleteProductId)
                .then()
                .statusCode(500); //TODO: global exception handler
    }

}
