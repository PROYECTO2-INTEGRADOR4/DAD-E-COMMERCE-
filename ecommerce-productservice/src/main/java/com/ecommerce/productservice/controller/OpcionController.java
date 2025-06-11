package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.domain.Opcion;
import com.ecommerce.productservice.service.IOpcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/opcion")
@RequiredArgsConstructor
public class OpcionController {

    private final IOpcionService service;

    @GetMapping
    public ResponseEntity<List<Opcion>> readAll() {
        try {
            List<Opcion> opciones = service.readAll();
            if (opciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(opciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opcion> findById(@PathVariable("id") Long id) {
        try {
            Opcion opcion = service.read(id).get();
            return new ResponseEntity<>(opcion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Opcion> createOpcion(@Valid @RequestBody Opcion o) {
        try {
            Opcion opcion = service.create(o);
            return new ResponseEntity<>(opcion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Opcion> deleteOpcion(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Opcion> updateOpcion(@PathVariable("id") Long id, @Valid @RequestBody Opcion o) {
        Optional<Opcion> opcion = service.read(id);
        if (opcion.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(o), HttpStatus.OK);
        }
    }
}
