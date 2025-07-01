package com.ecommerce.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificaciónRequestDto {
    private String to;
    private String type;
    private String username;
}
