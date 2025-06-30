package com.ecommerce.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcommerceReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceReviewServiceApplication.class, args);
    }
}
