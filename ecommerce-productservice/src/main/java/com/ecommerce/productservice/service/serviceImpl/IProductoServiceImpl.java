package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.Producto;
import com.ecommerce.productservice.repository.IProductoRepository;
import com.ecommerce.productservice.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IProductoServiceImpl implements IProductoService {
    private final IProductoRepository repository;
    @Override
    public Producto create(Producto p) {
        return repository.save(p);
    }

    @Override
    public Producto update(Producto p) {
        return repository.save(p);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Producto> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Producto> readAll() {
        return repository.findAll();
    }
}
