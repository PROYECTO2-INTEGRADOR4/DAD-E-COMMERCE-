package com.ecomerce.notificationservice.dto;

import lombok.Data;

@Data
public class SmsRequestDto {
    private String toPhone;   // Destino
    private String message;   // Texto del SMS
}
