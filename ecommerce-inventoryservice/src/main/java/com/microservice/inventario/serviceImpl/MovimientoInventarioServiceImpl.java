package com.microservice.inventario.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.inventario.entity.MovimientoInventario;
import com.microservice.inventario.repository.MovimientoInventarioRepository;
import com.microservice.inventario.service.MovimientoInventarioService;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository repository;

    @Override
    public MovimientoInventario create(MovimientoInventario movimiento) {
        return repository.save(movimiento);
    }

    @Override
    public MovimientoInventario update(MovimientoInventario movimiento) {
        return repository.save(movimiento);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<MovimientoInventario> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MovimientoInventario> readAll() {
        return repository.findAll();
    }
}
