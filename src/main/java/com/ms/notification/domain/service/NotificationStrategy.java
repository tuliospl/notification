package com.ms.notification.domain.service;

import com.ms.notification.domain.model.Notification;

public interface NotificationStrategy {
    void send(Notification notification);
    boolean supports(Notification notification);
} 