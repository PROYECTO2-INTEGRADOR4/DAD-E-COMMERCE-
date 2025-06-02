package com.example.ecommerceauth.service;

import com.example.ecommerceauth.dto.LoginDto;
import com.example.ecommerceauth.http.response.UsuarioResponse;

public interface IAuthService {
    String login(LoginDto loginDto);

    UsuarioResponse findByUsername(String username);
}
