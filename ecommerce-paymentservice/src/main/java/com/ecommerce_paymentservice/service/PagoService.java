package com.ecommerce_paymentservice.service;

import java.util.List;
import java.util.Optional;
import com.ecommerce_paymentservice.entity.Pago;

public interface PagoService {
    Pago create(Pago pago);
    Pago update(Pago pago);
    void delete(Long id);
    Optional<Pago> read(Long id);
    List<Pago> readAll();
}
