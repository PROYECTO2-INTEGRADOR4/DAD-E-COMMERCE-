package com.ecommerce_paymentservice.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_paymentservice.entity.Pago;
import com.ecommerce_paymentservice.repository.PagoRepository;
import com.ecommerce_paymentservice.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository repository;

    @Override
    public Pago create(Pago pago) {
        return repository.save(pago);
    }

    @Override
    public Pago update(Pago pago) {
        return repository.save(pago);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Pago> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pago> readAll() {
        return repository.findAll();
    }
}
