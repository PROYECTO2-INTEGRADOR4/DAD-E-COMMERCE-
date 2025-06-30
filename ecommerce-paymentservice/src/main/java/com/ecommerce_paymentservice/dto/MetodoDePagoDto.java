package com.ecommerce_paymentservice.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetodoDePagoDto {
    private Long id;
    private String tipo;
    private String detallesTokenizados;
    private String fechaExpiracion;
    private String marca;
}
