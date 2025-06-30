package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.Producto;
import com.ecommerce.productservice.dto.ProductoCatalogoDto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Producto create(Producto p);
    Producto update(Producto p);
    void delete(Long id);
    Optional<Producto> read(Long id);
    List<Producto> readAll();
    List<ProductoCatalogoDto> obtenerProductoCatalogo();
}
