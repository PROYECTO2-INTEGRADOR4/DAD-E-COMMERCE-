package com.microservice.inventario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productoId; // Solo ID, sin relación JPA para evitar acoplamiento

    @ManyToOne(optional = false)
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;

    @Column(nullable = false)
    private Integer disponible; // "cantidad_disponible" -> "disponible"

    @Column(nullable = false)
    private Integer reservado; // "cantidad_reservada" -> "reservado"

    @Column(nullable = false)
    private Integer minimo; // "cantidad_minima" -> "minimo" (para alertas)

    @Column(nullable = false)
    private LocalDateTime ultimaActualizacion;
}
