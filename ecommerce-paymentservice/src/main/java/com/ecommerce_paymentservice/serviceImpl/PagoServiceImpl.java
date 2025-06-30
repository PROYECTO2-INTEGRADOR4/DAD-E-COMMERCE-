package com.ecommerce_paymentservice.serviceImpl;

import java.util.List;
import java.util.Optional;

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
        return repository.save(pago);
    }

    @Override
    public Pago update(Pago pago) {  // Cambiar de MetodoDePago a Pago
        return repository.save(pago);
    }

    @Override
    public void delete(Long id) {  // Cambiar de MetodoDePago a Pago
        repository.deleteById(id);
    }

    @Override
    public Optional<Pago> read(Long id) {  // Cambiar de MetodoDePago a Pago
        return repository.findById(id);
    }

    @Override
    public List<Pago> readAll() {  // Cambiar de MetodoDePago a Pago
        return repository.findAll();
    }
}
