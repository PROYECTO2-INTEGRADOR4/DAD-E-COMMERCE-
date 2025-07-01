package com.ecomerce.notificationservice.serviceimpl;

import com.ecomerce.notificationservice.dto.NotificacionRequestDto;
import com.ecomerce.notificationservice.entity.Notification;

import com.ecomerce.notificationservice.repository.NotificationRepository;
import com.ecomerce.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private final JavaMailSender mailSender;

    @Override
    public void enviarConPlantilla(NotificacionRequestDto request) {

        String subject;
        String body;

        if ("LOGIN".equalsIgnoreCase(request.getType())) {
            subject = "🔒 Inicio de sesión exitoso";
            body = "Hola " + request.getUsername() + ",\n\n"
                    + "Te informamos que has iniciado sesión correctamente en nuestra plataforma.\n"
                    + "Si no fuiste tú quien realizó este inicio de sesión, por favor cambia tu contraseña de inmediato y contacta a nuestro equipo de soporte.\n\n"
                    + "¡Gracias por confiar en nosotros!\n"
                    + "Saludos cordiales.";
        } else if ("REGISTER".equalsIgnoreCase(request.getType())) {
            subject = "🎉 Registro exitoso";
            body = "¡Bienvenido/a " + request.getUsername() + "!\n\n"
                    + "Tu cuenta ha sido creada correctamente.\n"
                    + "Estamos encantados de tenerte como parte de nuestra comunidad.\n"
                    + "No dudes en explorar nuestros servicios y contactar a soporte si necesitas ayuda.\n\n"
                    + "¡Que tengas un excelente día!\n"
                    + "Atentamente, el equipo de soporte.";
        } else if ("RESET_PASSWORD".equalsIgnoreCase(request.getType())) {
            subject = "🔐 Contraseña restablecida con éxito";
            body = "Hola " + request.getUsername() + ",\n\n"
                    + "Queremos confirmarte que tu contraseña ha sido restablecida correctamente.\n"
                    + "Si tú no realizaste esta acción, cambia tu contraseña inmediatamente y comunícate con el soporte técnico.\n\n"
                    + "Gracias por mantener segura tu cuenta.\n"
                    + "Atentamente, el equipo de soporte.";
        } else {
            subject = "📢 Notificación";
            body = "Hola " + request.getUsername() + ",\n\n"
                    + "Te enviamos esta notificación para mantenerte informado sobre tus actividades en nuestra plataforma.\n\n"
                    + "Para cualquier consulta, estamos siempre a tu disposición.\n\n"
                    + "Saludos cordiales.";
        }


        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(request.getTo());
        mensaje.setSubject(subject);
        mensaje.setText(body);
        mensaje.setFrom("tu_correo@gmail.com");

        mailSender.send(mensaje);

    }
}

