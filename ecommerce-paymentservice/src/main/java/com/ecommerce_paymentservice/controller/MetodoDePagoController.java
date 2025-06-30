package com.ecommerce_paymentservice.controller;

import java.util.List;
import java.util.Optional;

import com.ecommerce_paymentservice.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce_paymentservice.entity.MetodoDePago;
import com.ecommerce_paymentservice.service.MetodoDePagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metodos")
@RequiredArgsConstructor
public class MetodoDePagoController {


    private final MetodoDePagoService service;

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoDePago> findById(@PathVariable Long id) {
        return service.read(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Crear nuevo (extrae userId del token)
    @PostMapping
    public ResponseEntity<?> create(HttpServletRequest request, @Valid @RequestBody MetodoDePago metodoDePago) {
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
            metodoDePago.setUsuarioId(userId);

            // Guardar el método de pago
            MetodoDePago saved = service.create(metodoDePago);
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
    public ResponseEntity<MetodoDePago> update(@PathVariable Long id, @Valid @RequestBody MetodoDePago metodoDePago) {
        Optional<MetodoDePago> opt = service.read(id);
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            metodoDePago.setId(id);
            MetodoDePago updated = service.update(metodoDePago);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
