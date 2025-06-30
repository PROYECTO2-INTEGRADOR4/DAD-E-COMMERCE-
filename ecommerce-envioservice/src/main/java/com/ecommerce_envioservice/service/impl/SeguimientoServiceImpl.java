package com.ecommerce_envioservice.service.impl;

import com.ecommerce_envioservice.entity.Seguimiento;
import com.ecommerce_envioservice.repository.SeguimientoRepository;
import com.ecommerce_envioservice.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Override
    public Seguimiento create(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public Seguimiento update(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public void delete(Long id) {
        seguimientoRepository.deleteById(id);
    }

    @Override
    public Optional<Seguimiento> read(Long id) {
        return seguimientoRepository.findById(id);
    }

    @Override
    public List<Seguimiento> readAll() {
        return seguimientoRepository.findAll();
    }
}
