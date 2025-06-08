package com.ecommerce.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "productservice")
public interface ProductClient {
}
