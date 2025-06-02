package com.microservice.inventario.service;

import java.util.List;
import java.util.Optional;
import com.microservice.inventario.entity.Almacen;

public interface AlmacenService {
    Almacen create(Almacen almacen);
    Almacen update(Almacen almacen);
    void delete(Long id);
    Optional<Almacen> read(Long id);
    List<Almacen> readAll();
}
