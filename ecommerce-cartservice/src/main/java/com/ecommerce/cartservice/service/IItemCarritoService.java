package com.ecommerce.cartservice.service;


import com.ecommerce.cartservice.domain.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface IItemCarritoService {
    ItemCarrito create(ItemCarrito ic);
    ItemCarrito update(ItemCarrito ic);
    void delete(ItemCarrito ic);
    Optional<ItemCarrito> read(Long id);
    List<ItemCarrito> readAll();
}
