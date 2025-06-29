package com.ecommerce.orderservice.service.serviceImpl;

import com.ecommerce.orderservice.domain.Orden;
import com.ecommerce.orderservice.repository.OrdenRepository;
import com.ecommerce.orderservice.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository repository;
    @Override
    public Orden create(Orden o) {
        return repository.save(o);
    }

    @Override
    public Orden update(Orden o) {
        return repository.save(o);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Orden> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Orden> readAll() {
        return repository.findAll();
    }
}
