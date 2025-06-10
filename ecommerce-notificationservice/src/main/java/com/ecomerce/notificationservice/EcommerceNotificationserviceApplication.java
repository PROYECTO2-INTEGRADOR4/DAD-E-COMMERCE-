package com.ecomerce.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommerceNotificationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceNotificationserviceApplication.class, args);
    }

}
