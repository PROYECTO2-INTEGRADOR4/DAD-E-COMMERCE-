package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.client.NotificationClient;
import com.ecommerce.userservice.config.JwtTokenProvider;
import com.ecommerce.userservice.domain.Rol;
import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.*;
import com.ecommerce.userservice.repository.IRolRepository;
import com.ecommerce.userservice.repository.IUsuarioRepository;
import com.ecommerce.userservice.service.IAuthService;
import com.ecommerce.userservice.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class IAuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IRolRepository iRolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IEmailService iEmailService;
    @Autowired
    private NotificationClient notificationClient;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = usuarioRepository.findByUsername(loginDto.getUsername()).orElseThrow();

        String token = jwtTokenProvider.generateToken(authentication, usuario.getId());

        notificationClient.enviarNotificacion(new NotificaciónRequestDto(
                usuario.getEmail(),
                "LOGIN_SUCCESS",
                usuario.getUsername()
        ));


        return token;
    }

    @Override
    public Usuario registerUser(RegistroDto registroDto) {
        if (usuarioRepository.findByUsername(registroDto.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        usuario.setEstado("A");

        Rol rolDefault = iRolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no existe"));

        usuario.setRoles(Set.of(rolDefault));
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // ✅ Llama notificación por plantilla
        notificationClient.enviarNotificacion(new NotificaciónRequestDto(
                savedUsuario.getEmail(),
                "REGISTER_WELCOME",
                savedUsuario.getUsername()
        ));

        return savedUsuario;
    }

    @Override
    public void solicitarCod(SolicitarCodDto solicitarCodDto) {
        Usuario usuario = usuarioRepository.findByEmail(solicitarCodDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email no registrado"));

        String codigo = UUID.randomUUID().toString().substring(0, 6);
        usuario.setCodigoReset(codigo);
        usuario.setResetExpira(LocalDateTime.now().plusMinutes(10));

        usuarioRepository.save(usuario);

        iEmailService.enviarCodigoReset(usuario.getEmail(), codigo);
    }

    @Override
    public Usuario restPassword(RestPasswordDto restPasswordDto) {
        Usuario usuario = usuarioRepository.findByEmail(restPasswordDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email no válido"));

        if (usuario.getCodigoReset() == null || !usuario.getCodigoReset().equals(restPasswordDto.getCodigo())) {
            throw new RuntimeException("Código inválido");
        }

        if (usuario.getResetExpira().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El código ha expirado");
        }

        usuario.setPassword(passwordEncoder.encode(restPasswordDto.getNewPassword()));
        usuario.setCodigoReset(null); // Limpia el código
        usuario.setResetExpira(null);
        return usuarioRepository.save(usuario);
    }
}