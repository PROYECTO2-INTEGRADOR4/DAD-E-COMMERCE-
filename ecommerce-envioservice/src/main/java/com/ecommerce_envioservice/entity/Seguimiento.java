package com.ecommerce_envioservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Seguimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "envioId", nullable = false)
    private Long envioId; // Relaciona con Envio

    @Column(name = "estado", length = 50, nullable = false)
    private String estado; // Ej: "En tránsito", "Entregado", "Retrasado"

    @Column(name = "ubicacionActual", length = 100)
    private String ubicacionActual;

    @Column(name = "observaciones", length = 200)
    private String observaciones;

    @Column(name = "fechaHoraRegistro", nullable = false)
    @DateTimeFormat
    private LocalDateTime fechaHoraRegistro;
}
