package com.ecomerce.notificationservice.dto;

import lombok.Data;

@Data
public class NotificacionRequestDto {
    private String to;
    private String type; // "LOGIN" o "REGISTER"
    private String username;
}
