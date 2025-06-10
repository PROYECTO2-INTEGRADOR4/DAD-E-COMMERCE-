package com.microservice.inventario.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.inventario.entity.Almacen;
import com.microservice.inventario.repository.AlmacenRepository;
import com.microservice.inventario.service.AlmacenService;

@Service
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenRepository repository;

    @Override
    public Almacen create(Almacen almacen) {
        return repository.save(almacen);
    }

    @Override
    public Almacen update(Almacen almacen) {
        return repository.save(almacen);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Almacen> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Almacen> readAll() {
        return repository.findAll();
    }
}
