package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.config.JwtTokenProvider;
import com.ecommerce.userservice.domain.Rol;
import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.LoginDto;
import com.ecommerce.userservice.dto.RegistroDto;
import com.ecommerce.userservice.repository.IRolRepository;
import com.ecommerce.userservice.repository.IUsuarioRepository;
import com.ecommerce.userservice.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        return usuarioRepository.save(usuario);
    }
}