package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.Marca;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {
    Marca create(Marca m);
    Marca update(Marca m);
    void delete(Long id);
    Optional<Marca> read(Long id);
    List<Marca> readAll();
}
