package com.ecommerce_paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcommercePaymentserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommercePaymentserviceApplication.class, args);
	}

}
