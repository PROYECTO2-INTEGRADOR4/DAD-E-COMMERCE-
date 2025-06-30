package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.ProductoVariante;
import com.ecommerce.productservice.dto.ProductoVarianteDto;

import java.util.List;
import java.util.Optional;

public interface IProductoVarianteService {
    ProductoVariante create(ProductoVariante pv);
    ProductoVariante update(ProductoVariante pv);
    void delete(Long id);
    Optional<ProductoVariante> read(Long id);
    List<ProductoVariante> readAll();
    ProductoVarianteDto readProductoVarianteforId(Long id);
}
