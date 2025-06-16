package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.config.JwtTokenProvider;
import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.LoginDto;
import com.ecommerce.userservice.repository.IUsuarioRepository;
import com.ecommerce.userservice.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IAuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = usuarioRepository.findByUsername(loginDto.getUsername()).orElseThrow();

        String token = jwtTokenProvider.generateToken(authentication, usuario.getId());

        return token;
    }
}