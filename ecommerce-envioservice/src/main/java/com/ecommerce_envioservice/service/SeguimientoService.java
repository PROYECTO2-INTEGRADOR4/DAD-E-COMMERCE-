package com.ecommerce_envioservice.service;

import com.ecommerce_envioservice.entity.Seguimiento;

import java.util.List;
import java.util.Optional;

public interface SeguimientoService {
    Seguimiento create (Seguimiento seguimiento);
    Seguimiento update (Seguimiento seguimiento);
    void delete (Long id);
    Optional<Seguimiento> read(Long id);
    List<Seguimiento> readAll();

}
