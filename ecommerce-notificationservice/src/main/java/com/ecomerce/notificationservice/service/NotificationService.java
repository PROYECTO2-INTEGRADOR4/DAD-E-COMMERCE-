package com.ecomerce.notificationservice.service;

import com.ecomerce.notificationservice.dto.NotificacionRequestDto;

public interface NotificationService {
    void enviarConPlantilla(NotificacionRequestDto request);
}
