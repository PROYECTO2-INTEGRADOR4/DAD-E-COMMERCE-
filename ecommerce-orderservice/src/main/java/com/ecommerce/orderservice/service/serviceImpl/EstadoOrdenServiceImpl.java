package com.ecommerce.orderservice.service.serviceImpl;

import com.ecommerce.orderservice.domain.EstadoOrden;
import com.ecommerce.orderservice.repository.EstadoOrdenRepository;
import com.ecommerce.orderservice.service.EstadoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoOrdenServiceImpl implements EstadoOrdenService {

    private final EstadoOrdenRepository repository;
    @Override
    public EstadoOrden create(EstadoOrden eo) {
        return repository.save(eo);
    }

    @Override
    public EstadoOrden update(EstadoOrden eo) {
        return repository.save(eo);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<EstadoOrden> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<EstadoOrden> readAll() {
        return repository.findAll();
    }
}
