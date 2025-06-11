package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.domain.Rol;
import com.ecommerce.userservice.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rol")
@RequiredArgsConstructor
public class RolController {

    private final IRolService service;

    @GetMapping
    public ResponseEntity<List<Rol>> readAll() {
        try {
            List<Rol> rols = service.readAll();
            if (rols.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rols, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable("id") Long id) {
        try {
            Rol rol = service.read(id).get();
            return new ResponseEntity<>(rol, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@Valid @RequestBody Rol r) {
        try {
            Rol rol = service.create(r);
            return new ResponseEntity<>(rol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rol> deleteRol(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable("id") Long id, @Valid @RequestBody Rol r) {
        Optional<Rol> rol = service.read(id);
        if (rol.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(r), HttpStatus.OK);
        }
    }
}
