package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.domain.Rol;
import com.ecommerce.userservice.dto.AuthResponseDto;
import com.ecommerce.userservice.dto.LoginDto;
import com.ecommerce.userservice.service.IAuthService;
import com.ecommerce.userservice.service.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUsuarioService usuarioService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        String token = authService.login(loginDto);

        var usuarioOpt = usuarioService.findByUsername(loginDto.getUsername());
        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        var usuario = usuarioOpt.get();

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);
        authResponseDto.setUsername(usuario.getUsername());
        authResponseDto.setUserId(usuario.getId());
        authResponseDto.setRoles(
                        usuario.getRoles().stream()
                        .map(Rol::getNombre)
                        .collect(Collectors.toList())
        );

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
