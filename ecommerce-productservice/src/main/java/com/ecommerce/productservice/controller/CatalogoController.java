package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductoCatalogoDto;
import com.ecommerce.productservice.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class CatalogoController {
    private final IProductoService productoService;

    @GetMapping("/catalogo")
    public ResponseEntity<List<ProductoCatalogoDto>> obtenerCatalogo() {
        List<ProductoCatalogoDto> catalogo = productoService.obtenerProductoCatalogo();
        return ResponseEntity.ok(catalogo);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
