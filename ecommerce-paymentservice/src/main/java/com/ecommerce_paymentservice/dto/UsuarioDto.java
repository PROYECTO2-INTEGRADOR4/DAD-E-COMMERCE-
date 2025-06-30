package com.ecommerce_paymentservice.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UsuarioDto {
    private Long id;
    private String username;
    private String estado;
    private Set<String> roles;

}
