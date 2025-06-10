package com.ecomerce.notificationservice.serviceimpl;

import com.ecomerce.notificationservice.entity.NotificationTemplate;
import com.ecomerce.notificationservice.repository.NotificationTemplateRepository;
import com.ecomerce.notificationservice.service.NotificationTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository repository;

    public NotificationTemplateServiceImpl(NotificationTemplateRepository repository) {
        this.repository = repository;
    }
    @Override
    public NotificationTemplate saveTemplate(NotificationTemplate template) {
        return repository.save(template);
    }

    @Override
    public NotificationTemplate getTemplateById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<NotificationTemplate> getAllTemplates() {
        return repository.findAll();
    }

    @Override
    public void deleteTemplate(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public NotificationTemplate updateTemplate(UUID id, NotificationTemplate newData) {
        return repository.findById(id).map(existingTemplate -> {
            existingTemplate.setType(newData.getType());
            existingTemplate.setChannel(newData.getChannel());
            existingTemplate.setSubject(newData.getSubject());
            existingTemplate.setBodyTemplate(newData.getBodyTemplate());
            existingTemplate.setLanguage(newData.getLanguage());
            return repository.save(existingTemplate);
        }).orElse(null);
    }



}
