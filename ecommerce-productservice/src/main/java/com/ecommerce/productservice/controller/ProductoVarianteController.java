package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.domain.ProductoVariante;
import com.ecommerce.productservice.dto.ProductoVarianteDto;
import com.ecommerce.productservice.service.IProductoVarianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto_variante")
@RequiredArgsConstructor
public class ProductoVarianteController {

    @Autowired
    private IProductoVarianteService service;

    @GetMapping
    public ResponseEntity<List<ProductoVariante>> readAll() {
        try {
            List<ProductoVariante> productoVariantes = service.readAll();
            if (productoVariantes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productoVariantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoVariante> findById(@PathVariable("id") Long id) {
        try {
            ProductoVariante productoVariante = service.read(id).get();
            return new ResponseEntity<>(productoVariante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductoVariante> createProductoVariante(@Valid @RequestBody ProductoVariante pv) {
        try {
            ProductoVariante productoVariante = service.create(pv);
            return new ResponseEntity<>(productoVariante, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoVariante> deleteProductoVariante(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoVariante> updateProductoVariante(@PathVariable("id") Long id, @Valid @RequestBody ProductoVariante pv) {
        Optional<ProductoVariante> productoVariante = service.read(id);
        if (productoVariante.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(pv), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoVarianteDto> getVarianteById(@PathVariable("id") Long id) {
        ProductoVarianteDto dto = service.readProductoVarianteforId(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
