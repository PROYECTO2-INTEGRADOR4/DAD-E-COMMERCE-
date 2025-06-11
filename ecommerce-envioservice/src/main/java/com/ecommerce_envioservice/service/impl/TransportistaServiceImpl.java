package com.ecommerce_envioservice.service.impl;

import com.ecommerce_envioservice.entity.Transportista;
import com.ecommerce_envioservice.repository.TransportistaRepository;
import com.ecommerce_envioservice.service.TransportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportistaServiceImpl implements TransportistaService {
    @Autowired
    TransportistaRepository transportistaRepository;

    @Override
    public Transportista create(Transportista transportista) {
        return transportistaRepository.save(transportista);
    }

    @Override
    public Transportista update(Transportista transportista) {
        return transportistaRepository.save(transportista);
    }

    @Override
    public void delete(Long id) {
        transportistaRepository.deleteById(id);
    }

    @Override
    public Optional<Transportista> read(Long id) {
        return transportistaRepository.findById(id);
    }

    @Override
    public List<Transportista> readAll() {
        return transportistaRepository.findAll();
    }
}
