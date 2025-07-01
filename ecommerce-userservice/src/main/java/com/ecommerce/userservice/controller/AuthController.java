package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.client.NotificationClient;
import com.ecommerce.userservice.domain.Rol;
import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.*;
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

    @Autowired
    private NotificationClient notificationClient;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.login(loginDto);

            var usuarioOpt = usuarioService.findByUsername(loginDto.getUsername());
            if (!usuarioOpt.isPresent()) {
                return new ResponseEntity("Usuario no encontrado", HttpStatus.UNAUTHORIZED);
            }

            var usuario = usuarioOpt.get();

            NotificaciónRequestDto request = new NotificaciónRequestDto();
            request.setTo(usuario.getEmail());
            request.setType("LOGIN");
            request.setUsername(usuario.getUsername());

            notificationClient.enviarNotificacion(request);

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

        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            return new ResponseEntity("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);

        } catch (Exception ex) {
            return new ResponseEntity("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroDto dto) {
        try {
            Usuario usuario = authService.registerUser(dto);
            NotificaciónRequestDto request = new NotificaciónRequestDto();
            request.setTo(usuario.getEmail());
            request.setType("REGISTER");
            request.setUsername(usuario.getUsername());

            notificationClient.enviarNotificacion(request);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/solicitar")
    public ResponseEntity<?> solicitarReset(@RequestBody SolicitarCodDto dto) {
        authService.solicitarCod(dto);
        return ResponseEntity.ok("Se ha enviado un código al correo");
    }

    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarReset(@RequestBody RestPasswordDto dto) {
        Usuario usuario = authService.restPassword(dto);
        NotificaciónRequestDto request = new NotificaciónRequestDto();
        request.setTo(usuario.getEmail());
        request.setType("RESET_PASSWORD");
        request.setUsername(usuario.getUsername());

        notificationClient.enviarNotificacion(request);
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }
}
