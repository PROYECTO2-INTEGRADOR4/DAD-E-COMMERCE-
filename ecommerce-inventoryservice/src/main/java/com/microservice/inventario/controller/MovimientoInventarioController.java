package com.microservice.inventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.inventario.entity.MovimientoInventario;
import com.microservice.inventario.service.MovimientoInventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService service;

    @GetMapping
    public ResponseEntity<List<MovimientoInventario>> readAll() {
        try {
            List<MovimientoInventario> list = service.readAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<MovimientoInventario> create(@Valid @RequestBody MovimientoInventario movimiento) {
        try {
            MovimientoInventario created = service.create(movimiento);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> getById(@PathVariable Long id) {
        try {
            Optional<MovimientoInventario> opt = service.read(id);
            if (opt.isPresent()) {
                return new ResponseEntity<>(opt.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventario> update(@PathVariable Long id, @Valid @RequestBody MovimientoInventario movimiento) {
        Optional<MovimientoInventario> opt = service.read(id);
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            movimiento.setId(id);
            MovimientoInventario updated = service.update(movimiento);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
