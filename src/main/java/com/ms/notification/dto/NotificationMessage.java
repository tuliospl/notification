package com.ms.notification.dto;

import com.ms.notification.enums.NotificationChannel;
import lombok.Data;

import java.util.List;

@Data
public class NotificationMessage {
    private String title;
    private String body;
    private List<String> recipient;
    private String sender;
    private List<NotificationChannel> channel;
}
