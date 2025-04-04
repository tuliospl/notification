package com.ms.notification.api.dto;

import com.ms.notification.domain.model.Notification;
import com.ms.notification.domain.model.NotificationChannel;
import com.ms.notification.domain.model.NotificationStatus;
import lombok.Data;

import java.util.List;

@Data
public class NotificationRequest {
    private List<String> recipients;
    private String sender;
    private String title;
    private String body;
    private NotificationChannel channel;

    public Notification toDomain() {
        return new Notification(
            recipients,
            sender,
            title,
            body,
            channel,
            NotificationStatus.PENDING
        );
    }
} 