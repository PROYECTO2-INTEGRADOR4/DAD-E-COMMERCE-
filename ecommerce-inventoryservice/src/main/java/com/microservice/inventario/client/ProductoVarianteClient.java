package com.microservice.inventario.client;

import com.microservice.inventario.dto.ProductoVarianteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-product")
public interface ProductoVarianteClient {

    @GetMapping("/api/producto_variante/{id}")
    ProductoVarianteDto obtenerProductoVariante(@PathVariable("id") Long id);
}
