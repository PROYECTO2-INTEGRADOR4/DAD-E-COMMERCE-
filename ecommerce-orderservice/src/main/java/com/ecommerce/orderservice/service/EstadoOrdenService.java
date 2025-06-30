package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.domain.EstadoOrden;

import java.util.List;
import java.util.Optional;

public interface EstadoOrdenService {
    EstadoOrden create(EstadoOrden eo);
    EstadoOrden update(EstadoOrden eo);
    void delete(Long id);
    Optional<EstadoOrden> read(Long id);
    List<EstadoOrden> readAll();
}
