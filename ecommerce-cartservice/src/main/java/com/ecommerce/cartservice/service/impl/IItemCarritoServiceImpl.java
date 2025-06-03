package com.ecommerce.cartservice.service.impl;

import com.ecommerce.cartservice.domain.ItemCarrito;
import com.ecommerce.cartservice.repository.IItemCarritoRepository;
import com.ecommerce.cartservice.service.IItemCarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IItemCarritoServiceImpl implements IItemCarritoService {
    private final IItemCarritoRepository repository;
    @Override
    public ItemCarrito create(ItemCarrito ic) {
        return repository.save(ic);
    }

    @Override
    public ItemCarrito update(ItemCarrito ic) {
        return repository.save(ic);
    }

    @Override
    public void delete(ItemCarrito ic) {
        repository.delete(ic);
    }

    @Override
    public Optional<ItemCarrito> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ItemCarrito> readAll() {
        return repository.findAll();
    }
}
