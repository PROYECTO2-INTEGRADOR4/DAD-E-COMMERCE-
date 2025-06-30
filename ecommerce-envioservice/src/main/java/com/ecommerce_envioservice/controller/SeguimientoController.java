package com.ecommerce_envioservice.controller;

<<<<<<< HEAD
=======
import com.ecommerce_envioservice.dto.SeguimientoDto;
>>>>>>> 596dad4 (Cambios envios probado)
import com.ecommerce_envioservice.entity.Seguimiento;
import com.ecommerce_envioservice.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
<<<<<<< HEAD
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
=======
@RequestMapping("/api/seguimientos")
@CrossOrigin(origins = "*")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    private SeguimientoDto convertToDTO(Seguimiento s) {
        SeguimientoDto dto = new SeguimientoDto();
        dto.setId(s.getId());
        dto.setEstado(s.getEstado());
        dto.setUbicacionActual(s.getUbicacionActual());
        dto.setObservaciones(s.getObservaciones());
        dto.setFechaHoraRegistro(s.getFechaHoraRegistro());
        if (s.getEnvio() != null) {
            dto.setEnvioId(s.getEnvio().getId());
        }
        return dto;
    }

    // Crear seguimiento
    @PostMapping
    public ResponseEntity<SeguimientoDto> createSeguimiento(@RequestBody Seguimiento seguimiento) {
        Seguimiento newSeguimiento = seguimientoService.create(seguimiento);
        return ResponseEntity.ok(convertToDTO(newSeguimiento));
    }

    // Obtener todos los seguimientos
    @GetMapping
    public ResponseEntity<List<SeguimientoDto>> getAllSeguimientos() {
        List<SeguimientoDto> dtos = seguimientoService.readAll().stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Obtener seguimiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoDto> getSeguimientoById(@PathVariable Long id) {
        Optional<Seguimiento> seguimiento = seguimientoService.read(id);
        return seguimiento.map(s -> ResponseEntity.ok(convertToDTO(s)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar seguimiento
    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoDto> updateSeguimiento(@PathVariable Long id, @RequestBody Seguimiento seguimiento) {
        seguimiento.setId(id);
        Seguimiento updatedSeguimiento = seguimientoService.update(seguimiento);
        return ResponseEntity.ok(convertToDTO(updatedSeguimiento));
    }

    // Eliminar seguimiento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeguimiento(@PathVariable Long id) {
>>>>>>> 596dad4 (Cambios envios probado)
        seguimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
