package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    Categoria create(Categoria c);
    Categoria update(Categoria c);
    void delete(Long id);
    Optional<Categoria> read(Long id);
    List<Categoria> readAll();
}
