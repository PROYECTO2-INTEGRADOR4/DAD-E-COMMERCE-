package com.ecommerce.cartservice.client;

import com.ecommerce.cartservice.dto.ProductoVarianteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-product")
public interface ProductClient {

    @GetMapping("/api/producto_variante/dto/{id}")
    ProductoVarianteDto getVariantesForId(@PathVariable("id") Long id);
}
