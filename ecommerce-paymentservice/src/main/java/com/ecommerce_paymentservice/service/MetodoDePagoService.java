package com.ecommerce_paymentservice.service;

import java.util.List;
import java.util.Optional;
import com.ecommerce_paymentservice.entity.MetodoDePago;

public interface MetodoDePagoService {
    MetodoDePago create(MetodoDePago metodoDePago);
    MetodoDePago update(MetodoDePago metodoDePago);
    void delete(Long id);
    Optional<MetodoDePago> read(Long id);
    List<MetodoDePago> readAll();
}
