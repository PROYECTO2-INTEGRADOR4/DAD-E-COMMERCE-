package com.ecommerce_paymentservice.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_paymentservice.entity.MetodoDePago;
import com.ecommerce_paymentservice.repository.MetodoDePagoRepository;
import com.ecommerce_paymentservice.service.MetodoDePagoService;

@Service
public class MetodoDePagoServiceImpl implements MetodoDePagoService {

    @Autowired
    private MetodoDePagoRepository repository;

    @Override
    public MetodoDePago create(MetodoDePago metodoDePago) {
        return repository.save(metodoDePago);
    }

    @Override
    public MetodoDePago update(MetodoDePago metodoDePago) {
        return repository.save(metodoDePago);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<MetodoDePago> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MetodoDePago> readAll() {
        return repository.findAll();
    }
}
