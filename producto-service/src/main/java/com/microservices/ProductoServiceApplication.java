package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.microservices.client")
@SpringBootApplication
public class ProductoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoServiceApplication.class, args);
	}

}
