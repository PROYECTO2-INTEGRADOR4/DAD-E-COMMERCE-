package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.LoginDto;

public interface IAuthService {
    String login(LoginDto loginDto);
}
