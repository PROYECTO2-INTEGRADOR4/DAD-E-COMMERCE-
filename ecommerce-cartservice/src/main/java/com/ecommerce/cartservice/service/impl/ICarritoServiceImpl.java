package com.ecommerce.cartservice.service.impl;

import com.ecommerce.cartservice.domain.Carrito;
import com.ecommerce.cartservice.repository.ICarritoRepository;
import com.ecommerce.cartservice.service.ICarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ICarritoServiceImpl implements ICarritoService {
    private final ICarritoRepository repository;
    @Override
    public Carrito create(Carrito c) {
        return repository.save(c);
    }

    @Override
    public Carrito update(Carrito c) {
        return repository.save(c);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Carrito> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Carrito> readAll() {
        return repository.findAll();
    }

    @Override
    public Carrito createCarritoForUser(Long userId) {
        Optional<Carrito> carrito = repository.findById(userId);
        if (carrito.isPresent()) {
            return carrito.get();
        }
        Carrito c = new Carrito();
        c.setUserId(userId);
        c.setEstado("A");
        return repository.save(c);
    }

    @Override
    public Optional<Carrito> readCarritoForUser(Long userId) {
        return repository.findByUserId(userId);
    }
}
