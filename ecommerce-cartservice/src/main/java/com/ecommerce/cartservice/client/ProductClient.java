package com.ecommerce.cartservice.client;

import com.ecommerce.cartservice.dto.ProductoVarianteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductClient {

    @GetMapping("/api/variantes/{id}")
    ProductoVarianteDto getVariantesForId(@PathVariable("id") Long id);
}
