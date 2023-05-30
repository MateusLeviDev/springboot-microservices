package com.levi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class LeviConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeviConfigServerApplication.class, args);
	}

}
