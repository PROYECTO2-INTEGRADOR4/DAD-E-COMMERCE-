package com.ecommerce.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarritoResponseDto {
    private Long id;
    private Long productoVarianteId;
    private String nombreproducto;
    private Map<String, String> atributos;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
