package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.domain.Rol;
import com.ecommerce.userservice.repository.IRolRepository;
import com.ecommerce.userservice.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IRolServiceImpl implements IRolService {
    private final IRolRepository repository;

    @Override
    public Rol create(Rol r) {
        return repository.save(r);
    }

    @Override
    public Rol update(Rol r) {
        return repository.save(r);
    }

    @Override
    public void delete(Rol r) {
        repository.delete(r);
    }

    @Override
    public Optional<Rol> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Rol> readAll() {
        return repository.findAll();
    }
}
