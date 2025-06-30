package com.ecommerce_envioservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD

public class Seguimiento {
=======
public class Seguimiento {

>>>>>>> 596dad4 (Cambios envios probado)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "envioId", nullable = false)
    private Long envioId; // Relaciona con Envio

    @Column(name = "estado", length = 50, nullable = false)
    private String estado; // Ej: "En tránsito", "Entregado", "Retrasado"
=======
    @Column(name = "estado", length = 50, nullable = false)
    private String estado;
>>>>>>> 596dad4 (Cambios envios probado)

    @Column(name = "ubicacionActual", length = 100)
    private String ubicacionActual;

    @Column(name = "observaciones", length = 200)
    private String observaciones;

    @Column(name = "fechaHoraRegistro", nullable = false)
    @DateTimeFormat
    private LocalDateTime fechaHoraRegistro;
<<<<<<< HEAD
=======

    @ManyToOne
    @JoinColumn(name = "envio_id", nullable = false)
    private Envio envio;

>>>>>>> 596dad4 (Cambios envios probado)
}
