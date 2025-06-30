package com.microservice.inventario.dto;

import com.microservice.inventario.entity.Almacen;
import lombok.Data;
import java.time.LocalDateTime;

@Data

public class InventarioConProductoDto {
    private Long id;
    private Integer disponible;
    private Integer reservado;
    private Integer minimo;
    private LocalDateTime ultimaActualizacion;
    private Almacen almacen;
    private ProductoVarianteDto productoVariante;
}
