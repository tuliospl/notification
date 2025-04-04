package com.ms.notification.domain.service;

import com.ms.notification.domain.model.Notification;

public interface NotificationService {
    void sendNotification(Notification notification);
} 