package com.ms.notification.infrastructure.persistence.mapper;

import com.ms.notification.domain.model.Notification;
import com.ms.notification.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    
    public Notification toDomain(NotificationEntity entity) {
        Notification notification = new Notification(
            entity.getRecipients(),
            entity.getSender(),
            entity.getTitle(),
            entity.getBody(),
            entity.getChannel(),
            entity.getStatus()
        );
        
        notification.setId(entity.getId());
        notification.setSentAt(entity.getSentAt());
        notification.setErrorMessage(entity.getErrorMessage());
        
        return notification;
    }
    
    public NotificationEntity toEntity(Notification notification) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(notification.getId());
        entity.setRecipients(notification.getRecipients());
        entity.setSender(notification.getSender());
        entity.setTitle(notification.getTitle());
        entity.setBody(notification.getBody());
        entity.setChannel(notification.getChannel());
        entity.setStatus(notification.getStatus());
        entity.setSentAt(notification.getSentAt());
        entity.setErrorMessage(notification.getErrorMessage());
        
        return entity;
    }
} 