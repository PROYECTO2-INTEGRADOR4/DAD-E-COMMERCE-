package com.example.ecomercenotificationservice.repository;

import com.example.ecomercenotificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository  extends JpaRepository<Notification, UUID> {
}
