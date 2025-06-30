package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.service.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IEmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCodigoReset(String to, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(to);
        mensaje.setSubject("Código de restablecimiento de contraseña");
        mensaje.setText("Tu código de verificación es: " + codigo + "\n\nEste código es válido por 10 minutos.");
        mensaje.setFrom("tu_correo@gmail.com");

        mailSender.send(mensaje);
    }
}
