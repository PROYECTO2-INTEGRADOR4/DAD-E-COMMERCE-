package com.ecommerce_envioservice.controller;

import com.ecommerce_envioservice.entity.Seguimiento;
import com.ecommerce_envioservice.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments") // Puedes cambiar "shipments" por "envios" si deseas
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
public class SeguimientoController {
    @Autowired
    private SeguimientoService seguimientoService;

    // Create shipment
    @PostMapping
    public ResponseEntity<Seguimiento> createShipment(@RequestBody Seguimiento seguimiento) {
        Seguimiento newSeguimiento = seguimientoService.create(seguimiento);
        return ResponseEntity.ok(newSeguimiento);
    }
    // Get all shipments
    @GetMapping
    public ResponseEntity<List<Seguimiento>> getAllShipments() {
        return ResponseEntity.ok(seguimientoService.readAll());
    }

    // Get shipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Seguimiento> getShipmentById(@PathVariable Long id) {
        Optional<Seguimiento> seguimiento = seguimientoService.read(id);
        return seguimiento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update shipment
    @PutMapping("/{id}")
    public ResponseEntity<Seguimiento> updateShipment(@PathVariable Long id, @RequestBody Seguimiento seguimiento) {
        seguimiento.setId(id); // set ID to make sure we update the right object
        Seguimiento updatedSeguimiento = seguimientoService.update(seguimiento);
        return ResponseEntity.ok(updatedSeguimiento);
    }

    // Delete shipment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        seguimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
