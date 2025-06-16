package com.ecommerce.cartservice.controller;

import com.ecommerce.cartservice.domain.Carrito;
import com.ecommerce.cartservice.service.ICarritoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final ICarritoService service;

    @GetMapping
    public ResponseEntity<List<Carrito>> readAll() {
        try {
            List<Carrito> carritos = service.readAll();
            if (carritos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carritos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> findById(@PathVariable("id") Long id) {
        try {
            Carrito carrito = service.read(id).get();
            return new ResponseEntity<>(carrito, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Carrito> createCarrito(@Valid @RequestBody Carrito c) {
        try {
            Carrito carrito = service.create(c);
            return new ResponseEntity<>(carrito, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carrito> deleteCarrito(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> updateCarrito(@PathVariable("id") Long id, @Valid @RequestBody Carrito c) {
        Optional<Carrito> carrito = service.read(id);
        if (carrito.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(c), HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Carrito> createCarritoUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Carrito carrito = service.createCarritoForUser(userId);
        return new ResponseEntity<>(carrito, HttpStatus.CREATED);
    }

    @GetMapping("/usuario")
    public ResponseEntity<Carrito> readCarritoUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return service.readCarritoForUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
