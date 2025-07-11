package com.ecommerce.userservice.service;

import com.ecommerce.userservice.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Usuario create(Usuario u);
    Usuario update(Usuario u);
    void delete(Long id);
    Optional<Usuario> read(Long id);
    List<Usuario> readAll();
    Optional<Usuario> findByUsername(String username);
    Usuario crearUsuario(Usuario u);
}

