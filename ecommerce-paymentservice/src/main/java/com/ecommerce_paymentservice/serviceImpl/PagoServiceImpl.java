package com.ecommerce_paymentservice.serviceImpl;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import com.ecommerce_paymentservice.client.OrdenClient;
import com.ecommerce_paymentservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ecommerce_paymentservice.entity.Pago;  // Cambiar de MetodoDePago a Pago
import com.ecommerce_paymentservice.repository.PagoRepository;  // Cambiar de MetodoDePagoRepository a PagoRepository
import com.ecommerce_paymentservice.service.PagoService;  // Cambiar de MetodoDePagoService a PagoService

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;
    // Cambiar de MetodoDePagoRepository a PagoRepository
    private final OrdenClient ordenClient;

    @Override
    public Pago create(Pago pago) {  // Cambiar de MetodoDePago a Pago
=======
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
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
        return repository.save(pago);
    }

    @Override
<<<<<<< HEAD
    public Pago update(Pago pago) {  // Cambiar de MetodoDePago a Pago
=======
    public Pago update(Pago pago) {
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
        return repository.save(pago);
    }

    @Override
<<<<<<< HEAD
    public void delete(Long id) {  // Cambiar de MetodoDePago a Pago
=======
    public void delete(Long id) {
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
        repository.deleteById(id);
    }

    @Override
<<<<<<< HEAD
    public Optional<Pago> read(Long id) {  // Cambiar de MetodoDePago a Pago
=======
    public Optional<Pago> read(Long id) {
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
        return repository.findById(id);
    }

    @Override
<<<<<<< HEAD
    public List<Pago> readAll() {  // Cambiar de MetodoDePago a Pago
=======
    public List<Pago> readAll() {
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
        return repository.findAll();
    }
}
