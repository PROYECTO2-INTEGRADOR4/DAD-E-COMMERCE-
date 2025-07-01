package com.ecomerce.notificationservice.controller;

import com.ecomerce.notificationservice.dto.NotificacionRequestDto;
import com.ecomerce.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody NotificacionRequestDto dto) {
        service.enviarConPlantilla(dto);
        return ResponseEntity.ok("Notificación enviada");
    }
}
