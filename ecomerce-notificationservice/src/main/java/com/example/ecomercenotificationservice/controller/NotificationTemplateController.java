package com.example.ecomercenotificationservice.controller;

import com.example.ecomercenotificationservice.entity.NotificationTemplate;
import com.example.ecomercenotificationservice.service.NotificationTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/templates")
public class NotificationTemplateController {

    private final NotificationTemplateService service;

    public NotificationTemplateController(NotificationTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NotificationTemplate> create(@RequestBody NotificationTemplate template) {
        return ResponseEntity.ok(service.saveTemplate(template));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTemplate> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getTemplateById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotificationTemplate>> getAll() {
        return ResponseEntity.ok(service.getAllTemplates());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationTemplate> update(@PathVariable UUID id, @RequestBody NotificationTemplate template) {
        return ResponseEntity.ok(service.updateTemplate(id, template));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
