package com.ecommerce_paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "metodos_pago")
public class MetodoDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID único del método de pago

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;  // ID del usuario (relación con el microservicio de usuarios)

    @Column(name = "tipo", nullable = false)
    private String tipo;  // Tipo de método de pago: credit_card, digital_wallet, etc.

    @Column(name = "detalles_tokenizados", nullable = false)
    private String detallesTokenizados;  // Detalles tokenizados (ej. últimos 4 dígitos de tarjeta, nombre en tarjeta)

    @Column(name = "fecha_expiracion")
    private String fechaExpiracion;  // Expiración para tarjetas, etc.

    @Column(name = "marca")
    private String marca;  // Marca del método de pago (ej. Visa, MasterCard, etc.)
}
