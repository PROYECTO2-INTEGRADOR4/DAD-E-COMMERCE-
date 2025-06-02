package com.example.ecommerceauth.service.impl;

import com.example.ecommerceauth.client.UsuarioClient;
import com.example.ecommerceauth.config.JwtTokenProvider;
import com.example.ecommerceauth.dto.LoginDto;
import com.example.ecommerceauth.dto.UsuarioDto;
import com.example.ecommerceauth.http.response.UsuarioResponse;
import com.example.ecommerceauth.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IAuthServiceImpl implements IAuthService {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public UsuarioResponse findByUsername(String username) {
        List<UsuarioDto> usuario = usuarioClient.findAllUsersByUsername(username);

        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        UsuarioDto usuarioDtos = usuario.get(0);

        return UsuarioResponse.builder()
                .username(usuarioDtos.getUsername())
                .estado(usuarioDtos.getEstado())
                .roles(new ArrayList<>(usuarioDtos.getRoles()))
                .build();
    }

}
