package com.ecommerce.productservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variante_opciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VarianteOpcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "valor")
    private String valor;
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "productovariante_id", nullable = false)
    private ProductoVariante productoVariante;

    @ManyToOne
    @JoinColumn(name = "opcion_id", nullable = false)
    private Opcion opcion;

}
