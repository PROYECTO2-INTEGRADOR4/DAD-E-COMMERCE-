package com.ecommerce.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDto {
    private String accessToken;
    private Long userId;
    private String username;
    private List<String> roles;

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}


