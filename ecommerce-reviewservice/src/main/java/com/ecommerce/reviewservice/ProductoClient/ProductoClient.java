package com.ecommerce.reviewservice.ProductoClient;


import com.ecommerce.reviewservice.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-product")
public interface ProductoClient {
    @GetMapping("/api/producto/{id}")
    ProductoDto findById(@PathVariable("id") Long id);
}
