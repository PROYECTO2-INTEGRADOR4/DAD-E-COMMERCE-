package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.domain.Marca;
import com.ecommerce.productservice.service.IMarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/marca")
@RequiredArgsConstructor
public class MarcaController {

    private final IMarcaService service;

    @GetMapping
    public ResponseEntity<List<Marca>> readAll() {
        try {
            List<Marca> marcas = service.readAll();
            if (marcas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(marcas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable("id") Long id) {
        try {
            Marca marca = service.read(id).get();
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Marca> createMarca(@Valid @RequestBody Marca m) {
        try {
            Marca marca = service.create(m);
            return new ResponseEntity<>(marca, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Marca> deleteMarca(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable("id") Long id, @Valid @RequestBody Marca m) {
        Optional<Marca> marca = service.read(id);
        if (marca.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(m), HttpStatus.OK);
        }
    }
}
