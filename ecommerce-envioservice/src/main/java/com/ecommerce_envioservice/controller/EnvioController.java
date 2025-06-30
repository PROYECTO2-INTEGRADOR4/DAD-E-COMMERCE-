package com.ecommerce_envioservice.controller;

<<<<<<< HEAD
=======
import com.ecommerce_envioservice.dto.EnvioDto;
>>>>>>> 596dad4 (Cambios envios probado)
import com.ecommerce_envioservice.entity.Envio;
import com.ecommerce_envioservice.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/shipments") // Puedes cambiar "shipments" por "envios" si deseas
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend

public class EnvioController {
    @Autowired
    private EnvioService envioService;

    // Create shipment
    @PostMapping
    public ResponseEntity<Envio> createShipment(@RequestBody Envio envio) {
        Envio newEnvio = envioService.create(envio);
        return ResponseEntity.ok(newEnvio);
    }
    // Get all shipments
    @GetMapping
    public ResponseEntity<List<Envio>> getAllShipments() {
        return ResponseEntity.ok(envioService.readAll());
    }

    // Get shipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Envio> getShipmentById(@PathVariable Long id) {
        Optional<Envio> envio = envioService.read(id);
        return envio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update shipment
    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateShipment(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id); // set ID to make sure we update the right object
        Envio updatedEnvio = envioService.update(envio);
        return ResponseEntity.ok(updatedEnvio);
    }

    // Delete shipment
=======
@RequestMapping("/api/envios")
@CrossOrigin(origins = "*")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    private EnvioDto convertToDTO(Envio envio) {
        EnvioDto dto = new EnvioDto();
        dto.setId(envio.getId());
        dto.setDireccionDestino(envio.getDireccionDestino());
        dto.setEstado(envio.getEstado());
        dto.setFechaEnvio(envio.getFechaEnvio());
        dto.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());
        if (envio.getTransportista() != null) {
            dto.setTransportistaId(envio.getTransportista().getId());
        }
        return dto;
    }

    @PostMapping
    public ResponseEntity<EnvioDto> createShipment(@RequestBody Envio envio) {
        Envio newEnvio = envioService.create(envio);
        return ResponseEntity.ok(convertToDTO(newEnvio));
    }

    @GetMapping
    public ResponseEntity<List<EnvioDto>> getAllShipments() {
        List<EnvioDto> dtos = envioService.readAll().stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDto> getShipmentById(@PathVariable Long id) {
        Optional<Envio> envio = envioService.read(id);
        return envio.map(e -> ResponseEntity.ok(convertToDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioDto> updateShipment(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.update(envio);
        return ResponseEntity.ok(convertToDTO(updatedEnvio));
    }

>>>>>>> 596dad4 (Cambios envios probado)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        envioService.delete(id);
        return ResponseEntity.noContent().build();
    }
<<<<<<< HEAD

=======
>>>>>>> 596dad4 (Cambios envios probado)
}
