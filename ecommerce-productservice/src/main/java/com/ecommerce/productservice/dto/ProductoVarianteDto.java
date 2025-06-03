package com.ecommerce.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoVarianteDto {
    private Long id;
    private String nombreproducto;
    private Map<String, String> atributos;
    private BigDecimal precio;
}
