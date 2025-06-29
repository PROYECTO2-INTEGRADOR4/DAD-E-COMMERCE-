package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.domain.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenService {
    Orden create(Orden o);
    Orden update(Orden o);
    void delete(Long id);
    Optional<Orden> read(Long id);
    List<Orden> readAll();
}
