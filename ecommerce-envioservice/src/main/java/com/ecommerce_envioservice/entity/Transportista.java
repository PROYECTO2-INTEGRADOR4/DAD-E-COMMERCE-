package com.ecommerce_envioservice.entity;

import jakarta.persistence.*;
import lombok.*;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 596dad4 (Cambios envios probado)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombreCompleto;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "estado", length = 20)
    private String estado; // ACTIVO, DISPONIBLE, OCUPADO, INACTIVO

<<<<<<< HEAD

=======
>>>>>>> 596dad4 (Cambios envios probado)
}
