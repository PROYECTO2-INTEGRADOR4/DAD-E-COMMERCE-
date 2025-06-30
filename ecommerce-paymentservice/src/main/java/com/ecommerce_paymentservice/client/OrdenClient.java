package com.ecommerce_paymentservice.client;


import com.ecommerce_paymentservice.dto.OrderDto;
import com.ecommerce_paymentservice.dto.PagoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-order")
public interface OrdenClient {
    @GetMapping("/api/orden/{id}")
    OrderDto findById(@PathVariable("id") Long id);
}
