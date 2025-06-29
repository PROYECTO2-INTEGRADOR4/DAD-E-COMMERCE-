package com.ecommerce.cartservice.service;

import com.ecommerce.cartservice.domain.Carrito;
import com.ecommerce.cartservice.dto.ItemCarritoResponseDto;

import java.util.List;
import java.util.Optional;

public interface ICarritoService {
    Carrito create(Carrito c);
    Carrito update(Carrito c);
    void delete(Long id);
    Optional<Carrito> read(Long id);
    List<Carrito> readAll();
    Carrito createCarritoForUser(Long userId);
    Optional<Carrito> readCarritoForUser(Long userId);
    List<ItemCarritoResponseDto> obtnerItemsPorIds(List<Long> itemIds, Long userId);
}
