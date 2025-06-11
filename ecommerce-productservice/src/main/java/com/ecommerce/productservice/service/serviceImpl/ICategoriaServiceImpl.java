package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.Categoria;
import com.ecommerce.productservice.repository.ICategoriaRepository;
import com.ecommerce.productservice.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ICategoriaServiceImpl implements ICategoriaService {
    private final ICategoriaRepository repository;
    @Override
    public Categoria create(Categoria c) {
        return repository.save(c);
    }

    @Override
    public Categoria update(Categoria c) {
        return repository.save(c);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Categoria> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Categoria> readAll() {
        return repository.findAll();
    }
}
