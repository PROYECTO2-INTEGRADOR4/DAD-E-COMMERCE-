package com.microservice.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.microservice.inventario.client")
public class EcommerceInventoryserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommerceInventoryserviceApplication.class, args);
	}

}
