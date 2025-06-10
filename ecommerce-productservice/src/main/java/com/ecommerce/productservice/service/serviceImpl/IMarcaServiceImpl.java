package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.Marca;
import com.ecommerce.productservice.repository.IMarcaRepository;
import com.ecommerce.productservice.service.IMarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IMarcaServiceImpl implements IMarcaService {
    private final IMarcaRepository repository;
    @Override
    public Marca create(Marca m) {
        return repository.save(m);
    }

    @Override
    public Marca update(Marca m) {
        return repository.save(m);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Marca> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Marca> readAll() {
        return repository.findAll();
    }
}
