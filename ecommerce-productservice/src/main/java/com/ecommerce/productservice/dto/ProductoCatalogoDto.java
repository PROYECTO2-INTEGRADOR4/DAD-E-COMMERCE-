package com.ecommerce.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCatalogoDto {
    private Long productoId;
    private String nombreProducto;
    private String imagenPrincipal;
    private BigDecimal precioMinimo;
    private List<String> coloresDisponibles;
}
