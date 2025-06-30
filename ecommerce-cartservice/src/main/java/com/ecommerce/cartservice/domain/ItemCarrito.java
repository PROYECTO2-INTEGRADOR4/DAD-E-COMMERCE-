package com.ecommerce.cartservice.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "itemcarritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCarrito {

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
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

}
