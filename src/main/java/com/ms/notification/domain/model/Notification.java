package com.ms.notification.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Notification {
    private Long id;
    private List<String> recipients;
    private String sender;
    private String title;
    private String body;
    private NotificationChannel channel;
    private NotificationStatus status;
    private LocalDateTime sentAt;
    private String errorMessage;

    public Notification(List<String> recipients, String sender, String title, String body, 
                       NotificationChannel channel, NotificationStatus status) {
        this.recipients = recipients;
        this.sender = sender;
        this.title = title;
        this.body = body;
        this.channel = channel;
        this.status = status;
        this.sentAt = LocalDateTime.now();
    }

    public Notification(String recipient, String sender, String title, String body, 
                       NotificationChannel channel, NotificationStatus status) {
        this.recipients = List.of(recipient);
        this.sender = sender;
        this.title = title;
        this.body = body;
        this.channel = channel;
        this.status = status;
        this.sentAt = LocalDateTime.now();
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }

    public void markAsError(String errorMessage) {
        this.status = NotificationStatus.ERROR;
        this.errorMessage = errorMessage;
    }
} 