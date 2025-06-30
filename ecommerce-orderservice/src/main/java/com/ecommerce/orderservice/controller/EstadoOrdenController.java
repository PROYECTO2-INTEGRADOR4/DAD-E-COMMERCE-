package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.domain.EstadoOrden;
import com.ecommerce.orderservice.domain.ItemOrden;
import com.ecommerce.orderservice.service.EstadoOrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado_orden")
@RequiredArgsConstructor
public class EstadoOrdenController {
    private final EstadoOrdenService service;

    @GetMapping
    public ResponseEntity<List<EstadoOrden>> readAll() {
        try {
            List<EstadoOrden> estadoOrdens = service.readAll();
            if (estadoOrdens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(estadoOrdens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoOrden> findById(@PathVariable("id") Long id) {
        try {
            EstadoOrden estadoOrden = service.read(id).get();
            return new ResponseEntity<>(estadoOrden, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EstadoOrden> createEstadoOrden(@Valid @RequestBody EstadoOrden eo) {
        try {
            EstadoOrden estadoOrden = service.create(eo);
            return new ResponseEntity<>(estadoOrden, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstadoOrden> deleteEstadoOrden(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoOrden> updateEstadoOrden(@PathVariable("id") Long id, @Valid @RequestBody EstadoOrden eo) {
        Optional<EstadoOrden> estadoOrden = service.read(id);
        if (estadoOrden.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(eo), HttpStatus.OK);
        }
    }
}
