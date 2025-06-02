package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.ProductoVariante;
import com.ecommerce.productservice.repository.IProductoVarianteRepository;
import com.ecommerce.productservice.service.IProductoVarianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IProductoVarianteServiceImpl implements IProductoVarianteService {
    private final IProductoVarianteRepository repository;
    @Override
    public ProductoVariante create(ProductoVariante pv) {
        return repository.save(pv);
    }

    @Override
    public ProductoVariante update(ProductoVariante pv) {
        return repository.save(pv);
    }

    @Override
    public void delete(ProductoVariante pv) {
        repository.delete(pv);
    }

    @Override
    public Optional<ProductoVariante> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProductoVariante> readAll() {
        return repository.findAll();
    }
}
