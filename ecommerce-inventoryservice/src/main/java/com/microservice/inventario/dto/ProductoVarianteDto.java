package com.microservice.inventario.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoVarianteDto {
    private Long id;
    private String nombreproducto;
    private BigDecimal precio;
}