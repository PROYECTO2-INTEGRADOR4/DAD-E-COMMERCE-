package com.ecommerce_paymentservice.serviceImpl;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
import org.springframework.stereotype.Service;

import com.ecommerce_paymentservice.entity.MetodoDePago;
import com.ecommerce_paymentservice.repository.MetodoDePagoRepository;
import com.ecommerce_paymentservice.service.MetodoDePagoService;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class MetodoDePagoServiceImpl implements MetodoDePagoService {

    private final MetodoDePagoRepository repository;
=======
public class MetodoDePagoServiceImpl implements MetodoDePagoService {

    @Autowired
    private MetodoDePagoRepository repository;
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02

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
<<<<<<< HEAD
}
=======
}
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
