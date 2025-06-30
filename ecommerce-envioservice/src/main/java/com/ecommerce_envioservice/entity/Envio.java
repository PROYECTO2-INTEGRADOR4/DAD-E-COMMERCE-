package com.ecommerce_envioservice.entity;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
>>>>>>> 596dad4 (Cambios envios probado)
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 596dad4 (Cambios envios probado)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccionDestino", length = 100)
    private String direccionDestino;
    @Column(name = "estado")
    private char estado;
    @Column(name = "fechaEnvio")
    @DateTimeFormat// pendiente, enviado, entregado
    private Date fechaEnvio;
    @Column(name = "fechaEntregaEstimada")
    @DateTimeFormat
    private LocalDate fechaEntregaEstimada;
<<<<<<< HEAD
=======

    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL)
    private List<Seguimiento> seguimientos;

    @ManyToOne
    @JoinColumn(name = "transportista_id")
    @JsonIgnoreProperties("envios")
    private Transportista transportista;

>>>>>>> 596dad4 (Cambios envios probado)
}
