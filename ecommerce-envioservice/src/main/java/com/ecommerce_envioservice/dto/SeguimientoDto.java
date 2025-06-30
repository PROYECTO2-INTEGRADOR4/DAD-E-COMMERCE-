package com.ecommerce_envioservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeguimientoDto {
    private Long id;
    private String estado;
    private String ubicacionActual;
    private String observaciones;
    private LocalDateTime fechaHoraRegistro;
    private Long envioId; // Solo el ID
}
