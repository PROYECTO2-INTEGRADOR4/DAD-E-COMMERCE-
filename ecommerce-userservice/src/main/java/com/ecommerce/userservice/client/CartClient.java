package com.ecommerce.userservice.client;

import com.ecommerce.userservice.dto.CrearCarritoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-cart")
public interface CartClient {

    @PostMapping("/api/carrito")
    void crearCarrito(@RequestBody CrearCarritoRequestDto request);
}
