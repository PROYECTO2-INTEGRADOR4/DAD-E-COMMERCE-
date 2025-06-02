package com.microservice.inventario.service;

import java.util.List;
import java.util.Optional;
import com.microservice.inventario.entity.MovimientoInventario;

public interface MovimientoInventarioService {
    MovimientoInventario create(MovimientoInventario movimiento);
    MovimientoInventario update(MovimientoInventario movimiento);
    void delete(Long id);
    Optional<MovimientoInventario> read(Long id);
    List<MovimientoInventario> readAll();
}
