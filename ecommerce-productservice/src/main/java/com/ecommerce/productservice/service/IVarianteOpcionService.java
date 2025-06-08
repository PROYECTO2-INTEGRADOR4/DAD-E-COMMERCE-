package com.ecommerce.productservice.service;

import com.ecommerce.productservice.domain.VarianteOpcion;

import java.util.List;
import java.util.Optional;

public interface IVarianteOpcionService {
    VarianteOpcion create(VarianteOpcion vo);
    VarianteOpcion update(VarianteOpcion vo);
    void delete(Long id);
    Optional<VarianteOpcion> read(Long id);
    List<VarianteOpcion> readAll();
}
