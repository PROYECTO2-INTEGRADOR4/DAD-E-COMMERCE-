package com.ecommerce.reviewservice.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "producto_id")
    private Long productoId;

    @Column(nullable = false, precision = 2, scale = 1, name = "calificacion")
    private BigDecimal calificacion;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    // ⚡️ ANOTACIONES PARA ACTUALIZAR AUTOMÁTICAMENTE
    @PrePersist
    protected void onCreate() {
        this.fechaPublicacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaPublicacion = LocalDateTime.now();
    }

}
