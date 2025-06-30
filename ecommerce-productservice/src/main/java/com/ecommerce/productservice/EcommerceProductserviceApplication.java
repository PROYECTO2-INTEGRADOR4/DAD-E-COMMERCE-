package com.ecommerce.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EcommerceProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductserviceApplication.class, args);
	}

}
