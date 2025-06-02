package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.Opcion;

import java.util.List;
import java.util.Optional;

public interface IOpcionService {
    Opcion create(Opcion o);
    Opcion update(Opcion p);
    void delete(Opcion p);
    Optional<Opcion> read(Long id);
    List<Opcion> readAll();
}
