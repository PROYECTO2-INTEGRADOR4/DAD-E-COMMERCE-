package com.ecommerce.userservice.dto;

import lombok.Data;

@Data
public class RestPasswordDto {
    private String newPassword;
    private String email;
    private String codigo;
}
