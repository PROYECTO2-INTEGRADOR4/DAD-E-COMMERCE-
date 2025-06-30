package com.ecommerce_paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID único del pago

    @Column(name = "orden_id", nullable = false)
    private Long ordenId;  // ID de la orden (relación con el microservicio de órdenes)

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId; // ID del usuario (relación con el microservicio de usuarios)

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "moneda", nullable = false)
    private String moneda;

    @Column(name = "estado", nullable = false)
    private String estado;  // Estado del pago: pending, completed, failed, refunded

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // Fecha de creación del pago

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // Fecha de la última actualización del pago

    @Column(name = "processed_at")
    private LocalDateTime processedAt;  // Fecha en la que el pago fue procesado (si aplica)

    // Relación con Método de Pago: Un pago tiene un solo método de pago
    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoDePago metodoDePago;  // Relación con la entidad Método de Pago
}
