package com.ms.notification.domain.repository;

import com.ms.notification.domain.model.Notification;
import java.util.List;

public interface NotificationRepository {
    Notification save(Notification notification);
    List<Notification> saveAll(List<Notification> notifications);
    List<Notification> findAll();
} 