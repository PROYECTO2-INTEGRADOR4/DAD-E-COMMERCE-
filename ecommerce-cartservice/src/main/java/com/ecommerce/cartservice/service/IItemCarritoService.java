package com.ecommerce.cartservice.service;


import com.ecommerce.cartservice.domain.ItemCarrito;
import com.ecommerce.cartservice.dto.ItemCarritoResponseDto;

import java.util.List;
import java.util.Optional;

public interface IItemCarritoService {
    ItemCarrito create(ItemCarrito ic);
    ItemCarrito update(ItemCarrito ic);
    void delete(Long id);
    Optional<ItemCarrito> read(Long id);
    List<ItemCarrito> readAll();
    ItemCarrito addItemCarrito(Long carritoId, Long productoVarianteId, Integer cantidad);
    List<ItemCarritoResponseDto> listarItemsPorCarritoId(Long carritoId);
}
