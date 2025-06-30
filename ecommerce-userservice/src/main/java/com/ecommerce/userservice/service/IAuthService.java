package com.ecommerce.userservice.service;

import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.LoginDto;
import com.ecommerce.userservice.dto.RegistroDto;
import com.ecommerce.userservice.dto.RestPasswordDto;
import com.ecommerce.userservice.dto.SolicitarCodDto;

public interface IAuthService {
    String login(LoginDto loginDto);
    Usuario registerUser(RegistroDto registroDto);
    void solicitarCod(SolicitarCodDto solicitarCodDto);
    Usuario restPassword(RestPasswordDto restPasswordDto);
}
