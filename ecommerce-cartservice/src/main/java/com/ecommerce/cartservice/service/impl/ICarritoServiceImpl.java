package com.ecommerce.cartservice.service.impl;

import com.ecommerce.cartservice.domain.Carrito;
import com.ecommerce.cartservice.service.ICarritoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICarritoServiceImpl implements ICarritoService {
    @Override
    public Carrito create(Carrito c) {
        return null;
    }

    @Override
    public Carrito update(Carrito c) {
        return null;
    }

    @Override
    public void delete(Carrito c) {

    }

    @Override
    public Optional<Carrito> read(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Carrito> readAll() {
        return List.of();
    }
}
