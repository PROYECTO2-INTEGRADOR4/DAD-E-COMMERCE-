package com.ecommerce.productservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "producto_variantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoVariante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "USK")
    private String USK;
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "imagen_url")
    private String imagen_url;
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productoVariante")
    private Set<VarianteOpcion> varianteOpciones;
}
