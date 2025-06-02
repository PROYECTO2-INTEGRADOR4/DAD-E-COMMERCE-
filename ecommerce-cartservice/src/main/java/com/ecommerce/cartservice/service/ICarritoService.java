package com.ecommerce.cartservice.service;

import com.ecommerce.cartservice.domain.Carrito;

import java.util.List;
import java.util.Optional;

public interface ICarritoService {
    Carrito create(Carrito c);
    Carrito update(Carrito c);
    void delete(Carrito c);
    Optional<Carrito> read(Long id);
    List<Carrito> readAll();
}
