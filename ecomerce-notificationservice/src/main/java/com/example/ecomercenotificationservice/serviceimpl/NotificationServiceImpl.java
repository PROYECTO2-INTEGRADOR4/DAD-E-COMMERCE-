package com.example.ecomercenotificationservice.serviceimpl;

import com.example.ecomercenotificationservice.entity.Notification;

import com.example.ecomercenotificationservice.repository.NotificationRepository;
import com.example.ecomercenotificationservice.service.NotificationService;

import org.springframework.stereotype.Service;


import java.util.List;

import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }
    @Override
    public Notification saveNotification(Notification notification) {
        return repository.save(notification);
    }

    @Override
    public Notification getNotificationById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    @Override
    public void deleteNotification(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Notification updateNotification(UUID id, Notification newData) {
        return repository.findById(id).map(existingNotification -> {
            existingNotification.setType(newData.getType());
            existingNotification.setChannel(newData.getChannel());
            existingNotification.setTemplateId(newData.getTemplateId());
            existingNotification.setStatus(newData.getStatus());
            existingNotification.setMetadata(newData.getMetadata());
            existingNotification.setScheduledAt(newData.getScheduledAt());
            existingNotification.setSentAt(newData.getSentAt());
            existingNotification.setErrorMessage(newData.getErrorMessage());
            return repository.save(existingNotification);
        }).orElse(null);
    }

}

