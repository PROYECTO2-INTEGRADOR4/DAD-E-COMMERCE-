package com.ecommerce.userservice.service;

public interface IEmailService {
    void enviarCodigoReset(String to, String codigo);
}
