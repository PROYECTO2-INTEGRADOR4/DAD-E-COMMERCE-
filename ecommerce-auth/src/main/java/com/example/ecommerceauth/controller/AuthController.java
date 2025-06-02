package com.example.ecommerceauth.controller;

import com.example.ecommerceauth.dto.AuthResponseDto;
import com.example.ecommerceauth.dto.LoginDto;
import com.example.ecommerceauth.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login (@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        var usuarioOpt = usuarioRepository.findByUsername(loginDto.getUsername());
        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        var usuario = usuarioOpt.get();

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);
        authResponseDto.setUsername(usuario.getUsername());
        authResponseDto.setRoles(
                usuario.getRoles().stream()
                        .map(Rol::getNombre)
                        .collect(Collectors.toList())
        );
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
