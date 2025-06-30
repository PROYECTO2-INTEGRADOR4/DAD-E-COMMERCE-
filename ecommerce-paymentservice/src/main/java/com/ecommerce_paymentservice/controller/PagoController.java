package com.ecommerce_paymentservice.controller;

import java.util.List;
import java.util.Optional;

import com.ecommerce_paymentservice.client.OrdenClient;
import com.ecommerce_paymentservice.config.JwtUtil;
import com.ecommerce_paymentservice.entity.MetodoDePago;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce_paymentservice.entity.Pago;
import com.ecommerce_paymentservice.service.PagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService service;

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable Long id) {
        return service.read(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Crear nuevo (extrae userId del token)
    @PostMapping
    public ResponseEntity<?> create(HttpServletRequest request, @Valid @RequestBody Pago pago) {
        try {
            // Obtener el token desde los encabezados
            String token = request.getHeader("Authorization");

            // Verificar si el token está presente y no vacío
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token no proporcionado.");
            }

            // Eliminar el prefijo "Bearer " si está presente
            token = token.replace("Bearer ", "");

            // Extraer el userId del token
            Long userId = JwtUtil.getUserId(token);

            // Verificar si el userId es nulo
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o no contiene el userId.");
            }

            // Asignar el userId al método de pago
            pago.setUsuarioId(userId);

            // Guardar el método de pago
            Pago saved = service.create(pago);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Producto no válido: " + ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Long id, @Valid @RequestBody Pago pago) {
        Optional<Pago> opt = service.read(id);
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            pago.setId(id);
            Pago updated = service.update(pago);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
