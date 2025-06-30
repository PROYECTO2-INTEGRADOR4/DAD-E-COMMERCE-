package com.ecommerce.userservice.service;

import com.ecommerce.userservice.domain.Usuario;
import com.ecommerce.userservice.dto.LoginDto;
import com.ecommerce.userservice.dto.RegistroDto;

public interface IAuthService {
    String login(LoginDto loginDto);
    Usuario registerUser(RegistroDto registroDto);
}
