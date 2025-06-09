package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.client.CartClient;
import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.CrearCarritoRequestDto;
import com.ecommerce.userservice.repository.IUsuarioRepository;
import com.ecommerce.userservice.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioRepository repository;
    private final CartClient cartClient;

    @Override
    public Usuario create(Usuario u) {
        return repository.save(u);
    }

    @Override
    public Usuario update(Usuario u) {
        return repository.save(u);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Usuario> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Usuario crearUsuario(Usuario u) {
        Usuario u1 = repository.save(u);
        CrearCarritoRequestDto request = new CrearCarritoRequestDto(u1.getId());
        cartClient.crearCarrito(request);
        return u1;
    }
}
