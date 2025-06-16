package com.microservice.inventario.controller;

import java.util.List;
import java.util.Optional;

import com.microservice.inventario.client.ProductoVarianteClient;
import com.microservice.inventario.dto.InventarioConProductoDto;
import com.microservice.inventario.dto.ProductoVarianteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.inventario.entity.Inventario;
import com.microservice.inventario.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    private final InventarioService service;
    private final ProductoVarianteClient productoVarianteClient;

    public InventarioController(InventarioService service, ProductoVarianteClient productoVarianteClient) {
        this.service = service;
        this.productoVarianteClient = productoVarianteClient;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> readAll() {
        try {
            List<Inventario> list = service.readAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> create(@Valid @RequestBody Inventario inventario) {
        try {
            Inventario created = service.create(inventario);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable Long id) {
        try {
            Optional<Inventario> opt = service.read(id);
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
    public ResponseEntity<Inventario> update(@PathVariable Long id, @Valid @RequestBody Inventario inventario) {
        Optional<Inventario> opt = service.read(id);
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            inventario.setId(id);
            Inventario updated = service.update(inventario);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 🔥 Nuevo endpoint para consultar el producto variante desde el microservicio de productos
    @GetMapping("/producto-variante/{id}")
    public ResponseEntity<ProductoVarianteDto> obtenerProductoVariante(@PathVariable Long id) {
        try {
            ProductoVarianteDto dto = productoVarianteClient.obtenerProductoVariante(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<InventarioConProductoDto>> listarConProducto() {
        try {
            List<Inventario> inventarios = service.readAll();
            if (inventarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<InventarioConProductoDto> resultado = inventarios.stream().map(inventario -> {
                InventarioConProductoDto dto = new InventarioConProductoDto();
                dto.setId(inventario.getId());
                dto.setDisponible(inventario.getDisponible());
                dto.setReservado(inventario.getReservado());
                dto.setMinimo(inventario.getMinimo());
                dto.setUltimaActualizacion(inventario.getUltimaActualizacion());
                dto.setAlmacen(inventario.getAlmacen());

                try {
                    dto.setProductoVariante(productoVarianteClient.obtenerProductoVariante(inventario.getProductoVarianteId()));
                } catch (Exception e) {
                    // En producción lo ideal sería usar un fallback o log
                    dto.setProductoVariante(null);
                }

                return dto;
            }).toList();

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
