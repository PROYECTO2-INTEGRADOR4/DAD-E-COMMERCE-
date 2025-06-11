package com.ecommerce.cartservice.controller;

import com.ecommerce.cartservice.domain.ItemCarrito;
import com.ecommerce.cartservice.dto.AgregarItemRequest;
import com.ecommerce.cartservice.service.IItemCarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itemcarrito")
@RequiredArgsConstructor
public class ItemCarritoController {

    private final IItemCarritoService service;

    @GetMapping
    public ResponseEntity<List<ItemCarrito>> readAll() {
        try {
            List<ItemCarrito> itemCarritos = service.readAll();
            if (itemCarritos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(itemCarritos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCarrito> findById(@PathVariable("id") Long id) {
        try {
            ItemCarrito itemCarrito = service.read(id).get();
            return new ResponseEntity<>(itemCarrito, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ItemCarrito> createItemCarrito(@Valid @RequestBody ItemCarrito ic) {
        try {
            ItemCarrito itemCarrito = service.create(ic);
            return new ResponseEntity<>(itemCarrito, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemCarrito> deleteItemCarrito(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCarrito> updateItemCarrito(@PathVariable("id") Long id, @Valid @RequestBody ItemCarrito ic) {
        Optional<ItemCarrito> itemCarrito = service.read(id);
        if (itemCarrito.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(ic), HttpStatus.OK);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<ItemCarrito> agregarItemCarrito(@RequestBody AgregarItemRequest request) {
        ItemCarrito nuevoItem = service.addItemCarrito(
                request.getCarritoId(),
                request.getProductoVarianteId(),
                request.getCantidad()
        );
        return new ResponseEntity<>(nuevoItem, HttpStatus.CREATED);
    }
}
