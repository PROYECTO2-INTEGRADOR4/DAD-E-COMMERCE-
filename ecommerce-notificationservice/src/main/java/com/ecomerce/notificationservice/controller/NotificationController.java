package com.ecomerce.notificationservice.controller;

import com.ecomerce.notificationservice.entity.Notification;
import com.ecomerce.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        return ResponseEntity.ok(service.saveNotification(notification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getNotificationById(id));
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(service.getAllNotifications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable UUID id,
            @RequestBody Notification notificationDetails) {

        Notification notification = service.getNotificationById(id);
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualiza los campos que quieres permitir modificar
        notification.setStatus(notificationDetails.getStatus());
        notification.setMetadata(notificationDetails.getMetadata());
        notification.setScheduledAt(notificationDetails.getScheduledAt());

        Notification updated = service.saveNotification(notification);
        return ResponseEntity.ok(updated);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
