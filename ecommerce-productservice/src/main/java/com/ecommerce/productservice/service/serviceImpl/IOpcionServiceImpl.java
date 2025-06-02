package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.Opcion;
import com.ecommerce.productservice.repository.IOpcionRepository;
import com.ecommerce.productservice.service.IOpcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IOpcionServiceImpl implements IOpcionService {
    private final IOpcionRepository repository;
    @Override
    public Opcion create(Opcion o) {
        return repository.save(o);
    }

    @Override
    public Opcion update(Opcion p) {
        return repository.save(p);
    }

    @Override
    public void delete(Opcion p) {
        repository.delete(p);
    }

    @Override
    public Optional<Opcion> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Opcion> readAll() {
        return repository.findAll();
    }
}
