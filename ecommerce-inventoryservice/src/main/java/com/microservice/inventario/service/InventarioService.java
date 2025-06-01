package com.microservice.inventario.service;

import java.util.List;
import java.util.Optional;
import com.microservice.inventario.entity.Inventario;

public interface InventarioService {
    Inventario create(Inventario inventario);
    Inventario update(Inventario inventario);
    void delete(Long id);
    Optional<Inventario> read(Long id);
    List<Inventario> readAll();
}
