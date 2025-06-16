package com.ecommerce_paymentservice.dto;

import com.ecommerce_paymentservice.entity.MetodoDePago;
import lombok.Data;
@Data
public class MetodoDePagoConUsuarioDto {
    private MetodoDePago metodoDePago;
    private UsuarioDto usuario;
}
