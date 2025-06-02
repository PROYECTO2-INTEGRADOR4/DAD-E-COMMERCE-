package com.microservice.inventario.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.inventario.entity.Inventario;
import com.microservice.inventario.repository.InventarioRepository;
import com.microservice.inventario.service.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository repository;

    @Override
    public Inventario create(Inventario inventario) {
        return repository.save(inventario);
    }

    @Override
    public Inventario update(Inventario inventario) {
        return repository.save(inventario);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Inventario> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Inventario> readAll() {
        return repository.findAll();
    }
}
