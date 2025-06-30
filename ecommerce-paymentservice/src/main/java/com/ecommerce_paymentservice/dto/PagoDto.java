package com.ecommerce_paymentservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoDto {
    private Long id;
    private Long ordenId;
    private String moneda;
    private String estado;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}
