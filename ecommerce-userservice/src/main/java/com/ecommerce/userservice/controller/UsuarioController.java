package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> readAll() {
        try {
            List<Usuario> usuarios = service.readAll();
            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {
        try {
            Usuario usuario = service.read(id).get();
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario u) {
        try {
            Usuario usuario = service.create(u);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/by-username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        Optional<Usuario> usuarioOpt = service.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOpt.get();

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario u) {
        Optional<Usuario> usuario = service.read(id);
        if (usuario.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(u), HttpStatus.OK);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("username", usuario.getUsername());
        response.put("password", usuario.getPassword());
        response.put("estado", usuario.getEstado());
        response.put("roles", usuario.getRoles().stream()
                .map(rol -> rol.getNombre()) // asumimos que tienes getNombre()
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(usuarioService.findByUsername(username));
    }
}
