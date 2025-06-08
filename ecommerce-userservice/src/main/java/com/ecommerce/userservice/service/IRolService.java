package com.ecommerce.userservice.service;


import com.ecommerce.userservice.domain.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    Rol create(Rol r);
    Rol update(Rol r);
    void delete(Long id);
    Optional<Rol> read(Long id);
    List<Rol> readAll();
}
