package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.domain.Producto;
import com.ecommerce.productservice.service.IProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> readAll() {
        try {
            List<Producto> productos = service.readAll();
            if (productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable("id") Long id) {
        try {
            Producto producto = service.read(id).get();
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto p) {
        try {
            Producto producto = service.create(p);
            return new ResponseEntity<>(producto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id, @Valid @RequestBody Producto p) {
        Optional<Producto> producto = service.read(id);
        if (producto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(p), HttpStatus.OK);
        }
    }
}
