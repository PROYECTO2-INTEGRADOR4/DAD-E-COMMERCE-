package com.ecomerce.notificationservice.repository;

import com.ecomerce.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository  extends JpaRepository<Notification, UUID> {
}
