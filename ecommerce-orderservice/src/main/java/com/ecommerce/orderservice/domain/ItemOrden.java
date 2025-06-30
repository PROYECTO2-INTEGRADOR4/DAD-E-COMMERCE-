package com.ecommerce.orderservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "item_orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "productovar_id")
    private Long productoVarianteId;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "preciounitario")
    private BigDecimal precioUnitario;
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;
}
