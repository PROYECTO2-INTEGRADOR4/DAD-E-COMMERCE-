package com.ecommerce_envioservice.service;

import com.ecommerce_envioservice.entity.Transportista;

import java.util.List;
import java.util.Optional;

public interface TransportistaService {
    Transportista create (Transportista transportista);
    Transportista update (Transportista transportista);
    void delete (Long id);
    Optional<Transportista> read(Long id);
    List<Transportista> readAll();
}
