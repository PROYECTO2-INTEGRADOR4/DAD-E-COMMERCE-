package com.ecomerce.notificationservice.service;

import com.ecomerce.notificationservice.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    Notification saveNotification(Notification notification);
    Notification getNotificationById(UUID id);
    List<Notification> getAllNotifications();
    void deleteNotification(UUID id);
    Notification updateNotification(UUID id, Notification notification);
}
