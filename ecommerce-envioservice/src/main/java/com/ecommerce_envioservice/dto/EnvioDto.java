package com.ecommerce_envioservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EnvioDto {
    private Long id;
    private String direccionDestino;
    private char estado;
    private Date fechaEnvio;
    private LocalDate fechaEntregaEstimada;
    private Long transportistaId; // Solo el ID
}
