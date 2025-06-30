package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.domain.ItemOrden;
import com.ecommerce.orderservice.service.ItemOrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item_orden")
@RequiredArgsConstructor
public class ItemOrdenController {

    private final ItemOrdenService service;

    @GetMapping
    public ResponseEntity<List<ItemOrden>> readAll() {
        try {
            List<ItemOrden> itemOrdens = service.readAll();
            if (itemOrdens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(itemOrdens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemOrden> findById(@PathVariable("id") Long id) {
        try {
            ItemOrden itemOrden = service.read(id).get();
            return new ResponseEntity<>(itemOrden, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ItemOrden> createItemOrden(@Valid @RequestBody ItemOrden io) {
        try {
            ItemOrden itemOrden = service.create(io);
            return new ResponseEntity<>(itemOrden, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemOrden> deleteItemOrden(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemOrden> updateItemOrden(@PathVariable("id") Long id, @Valid @RequestBody ItemOrden io) {
        Optional<ItemOrden> itemOrden = service.read(id);
        if (itemOrden.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(io), HttpStatus.OK);
        }
    }
}
