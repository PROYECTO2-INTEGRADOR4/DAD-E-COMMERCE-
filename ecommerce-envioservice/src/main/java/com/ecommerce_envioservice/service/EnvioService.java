package com.ecommerce_envioservice.service;

import com.ecommerce_envioservice.entity.Envio;
import java.util.List;
import java.util.Optional;

public interface EnvioService {
    Envio create(Envio envio);
    Envio update(Envio envio);
    void delete(Long id);
    Optional<Envio> read(Long id);
    List<Envio> readAll();
}
