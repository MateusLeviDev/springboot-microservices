package com.levi.microservices.client;

import org.springframework.boot.SpringApplication;

public class TestClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ClientServiceApplication::main).with(TestContainersConfiguration.class).run(args);
	}

}
