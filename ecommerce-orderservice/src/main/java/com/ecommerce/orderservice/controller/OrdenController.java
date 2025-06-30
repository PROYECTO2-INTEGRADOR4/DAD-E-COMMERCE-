package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.domain.Orden;
import com.ecommerce.orderservice.dto.CrearOrdenRequest;
import com.ecommerce.orderservice.service.OrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService service;

    @GetMapping
    public ResponseEntity<List<Orden>> readAll() {
        try {
            List<Orden> ordens = service.readAll();
            if (ordens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ordens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> findById(@PathVariable("id") Long id) {
        try {
            Orden orden = service.read(id).get();
            return new ResponseEntity<>(orden, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Orden> createOrden(@Valid @RequestBody Orden o) {
        try {
            Orden orden = service.create(o);
            return new ResponseEntity<>(orden, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orden> deleteOrden(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> updateOrden(@PathVariable("id") Long id, @Valid @RequestBody Orden o) {
        Optional<Orden> orden = service.read(id);
        if (orden.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(o), HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Orden> crearOrdenPorId(@RequestHeader("userId") Long userId, @RequestBody CrearOrdenRequest request) {
        Orden orden = service.crearOrden(request, userId);
        return new ResponseEntity<>(orden, HttpStatus.CREATED);
    }
}
