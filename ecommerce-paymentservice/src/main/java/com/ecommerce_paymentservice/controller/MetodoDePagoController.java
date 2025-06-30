package com.ecommerce_paymentservice.controller;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import com.ecommerce_paymentservice.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
=======
import com.ecommerce_paymentservice.client.UsuarioClient;
import com.ecommerce_paymentservice.dto.MetodoDePagoConUsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce_paymentservice.entity.MetodoDePago;
import com.ecommerce_paymentservice.service.MetodoDePagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metodos")
<<<<<<< HEAD
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
=======
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoService service;
    private UsuarioClient usuarioClient;

    @GetMapping
    public ResponseEntity<List<MetodoDePago>> readAll() {
        try {
            List<MetodoDePago> list = service.readAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<MetodoDePago> create(@Valid @RequestBody MetodoDePago metodoDePago) {
        try {
            MetodoDePago created = service.create(metodoDePago);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoDePago> getById(@PathVariable Long id) {
        try {
            Optional<MetodoDePago> opt = service.read(id);
            if (opt.isPresent()) {
                return new ResponseEntity<>(opt.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
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
<<<<<<< HEAD
=======

    @GetMapping("/detalle")
    public ResponseEntity<List<MetodoDePagoConUsuarioDto>> listarConUsuario() {
        try {
            List<MetodoDePago> metodos = service.readAll();
            if (metodos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<MetodoDePagoConUsuarioDto> resultado = metodos.stream().map(m -> {
                MetodoDePagoConUsuarioDto dto = new MetodoDePagoConUsuarioDto();
                dto.setMetodoDePago(m);
                try {
                    dto.setUsuario(usuarioClient.obtenerUsuarioPorId(m.getUsuarioId()));
                } catch (Exception e) {
                    dto.setUsuario(null); // o puedes manejar con logs
                }
                return dto;
            }).toList();

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
>>>>>>> 001c87ff004fb87d5103f9b5844071a2f4d27d02
}
