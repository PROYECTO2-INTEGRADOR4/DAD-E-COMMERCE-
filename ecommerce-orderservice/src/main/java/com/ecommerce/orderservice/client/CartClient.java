package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.dto.ItemCarritoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "msvc-cart")
public interface CartClient {

    @PostMapping("api/carrito/items/ids")
    List<ItemCarritoResponseDto> obtenerItemsPorIds(@RequestBody List<Long> itemIds, @RequestHeader("userId") Long userId);
}
