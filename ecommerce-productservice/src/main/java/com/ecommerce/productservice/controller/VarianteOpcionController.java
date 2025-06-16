package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.domain.VarianteOpcion;
import com.ecommerce.productservice.service.IOpcionService;
import com.ecommerce.productservice.service.IVarianteOpcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/varianteOpcion")
@RequiredArgsConstructor
public class VarianteOpcionController {

    private final IVarianteOpcionService service;

    @GetMapping
    public ResponseEntity<List<VarianteOpcion>> readAll() {
        try {
            List<VarianteOpcion> varianteOpcions = service.readAll();
            if (varianteOpcions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(varianteOpcions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VarianteOpcion> findById(@PathVariable("id") Long id) {
        try {
            VarianteOpcion varianteOpcion = service.read(id).get();
            return new ResponseEntity<>(varianteOpcion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VarianteOpcion> createVarianteOpcion(@Valid @RequestBody VarianteOpcion vo) {
        try {
            VarianteOpcion varianteOpcion = service.create(vo);
            return new ResponseEntity<>(varianteOpcion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VarianteOpcion> deleteVarianteOpcion(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VarianteOpcion> updateVarianteOpcion(@PathVariable("id") Long id, @Valid @RequestBody VarianteOpcion vo) {
        Optional<VarianteOpcion> varianteOpcion = service.read(id);
        if (varianteOpcion.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(vo), HttpStatus.OK);
        }
    }
}
