package com.example.ecomercenotificationservice.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTemplate{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String channel;

    private String subject;

    @Column(name = "body_template", nullable = false)
    private String bodyTemplate;

    @Column(nullable = false)
    private String language = "es";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}

