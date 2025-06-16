package com.ecommerce_paymentservice.controller;

import java.util.List;
import java.util.Optional;

import com.ecommerce_paymentservice.client.UsuarioClient;
import com.ecommerce_paymentservice.dto.MetodoDePagoConUsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce_paymentservice.entity.MetodoDePago;
import com.ecommerce_paymentservice.service.MetodoDePagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metodos")
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
}
