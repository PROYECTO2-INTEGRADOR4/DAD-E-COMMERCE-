package com.microservice.inventario.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "almacenes")
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion; // Combina dirección y ubicación en un solo campo

    @Column(nullable = false)
    private boolean activo; // Reemplaza "estado" por booleano (true=activo, false=inactivo)
}
