package com.ecommerce_envioservice.controller;

import com.ecommerce_envioservice.entity.Transportista;
import com.ecommerce_envioservice.service.TransportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/shipments") // Puedes cambiar "shipments" por "envios" si deseas
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
public class TransportistaController {
    @Autowired
    private TransportistaService transportistaService;

    // Create shipment
    @PostMapping
    public ResponseEntity<Transportista> createShipment(@RequestBody Transportista transportista) {
        Transportista newTransportista = transportistaService.create(transportista);
        return ResponseEntity.ok(newTransportista);
    }
    // Get all shipments
    @GetMapping
    public ResponseEntity<List<Transportista>> getAllShipments() {
        return ResponseEntity.ok(transportistaService.readAll());
    }

    // Get shipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transportista> getShipmentById(@PathVariable Long id) {
=======
@RequestMapping("/api/transportistas")
@CrossOrigin(origins = "*")
public class TransportistaController {

    @Autowired
    private TransportistaService transportistaService;

    // Crear transportista
    @PostMapping
    public ResponseEntity<Transportista> createTransportista(@RequestBody Transportista transportista) {
        Transportista newTransportista = transportistaService.create(transportista);
        return ResponseEntity.ok(newTransportista);
    }

    // Obtener todos los transportistas
    @GetMapping
    public ResponseEntity<List<Transportista>> getAllTransportistas() {
        return ResponseEntity.ok(transportistaService.readAll());
    }

    // Obtener transportista por ID
    @GetMapping("/{id}")
    public ResponseEntity<Transportista> getTransportistaById(@PathVariable Long id) {
>>>>>>> 596dad4 (Cambios envios probado)
        Optional<Transportista> transportista = transportistaService.read(id);
        return transportista.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

<<<<<<< HEAD
    // Update shipment
    @PutMapping("/{id}")
    public ResponseEntity<Transportista> updateShipment(@PathVariable Long id, @RequestBody Transportista transportista) {
        transportista.setId(id); // set ID to make sure we update the right object
=======
    // Actualizar transportista
    @PutMapping("/{id}")
    public ResponseEntity<Transportista> updateTransportista(@PathVariable Long id, @RequestBody Transportista transportista) {
        transportista.setId(id);
>>>>>>> 596dad4 (Cambios envios probado)
        Transportista updatedTransportista = transportistaService.update(transportista);
        return ResponseEntity.ok(updatedTransportista);
    }

<<<<<<< HEAD
    // Delete shipment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        transportistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

=======
    // Eliminar transportista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportista(@PathVariable Long id) {
        transportistaService.delete(id);
        return ResponseEntity.noContent().build();
    }
>>>>>>> 596dad4 (Cambios envios probado)
}
