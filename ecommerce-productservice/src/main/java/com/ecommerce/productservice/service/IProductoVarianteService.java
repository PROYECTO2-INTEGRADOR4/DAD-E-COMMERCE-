package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.ProductoVariante;

import java.util.List;
import java.util.Optional;

public interface IProductoVarianteService {
    ProductoVariante create(ProductoVariante pv);
    ProductoVariante update(ProductoVariante pv);
    void delete(ProductoVariante pv);
    Optional<ProductoVariante> read(Long id);
    List<ProductoVariante> readAll();
}
