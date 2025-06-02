package com.example.ecomercenotificationservice.service;

import com.example.ecomercenotificationservice.entity.NotificationTemplate;

import java.util.List;
import java.util.UUID;

public interface NotificationTemplateService {
    NotificationTemplate saveTemplate(NotificationTemplate template);
    NotificationTemplate getTemplateById(UUID id);
    List<NotificationTemplate> getAllTemplates();
    void deleteTemplate(UUID id);
    NotificationTemplate updateTemplate(UUID id, NotificationTemplate template);
}
