package com.ecommerce_envioservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccionDestino", length = 100)
    private String direccionDestino;
    @Column(name = "estado")
    private char estado;
    @Column(name = "fechaEnvio")
    @DateTimeFormat// pendiente, enviado, entregado
    private Date fechaEnvio;
    @Column(name = "fechaEntregaEstimada")
    @DateTimeFormat
    private LocalDate fechaEntregaEstimada;
}
