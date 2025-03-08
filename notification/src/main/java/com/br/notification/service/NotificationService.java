package com.br.notification.service;

import com.br.notification.controller.DTO.NotificationRequestDTO;
import com.br.notification.domain.Notification;
import com.br.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(NotificationRequestDTO notificationRequest) {
        Notification notification =  new Notification(notificationRequest);
        return notificationRepository.save(notification);
    }

    public Optional<Notification> getNotification(String id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        return notification != null ? Optional.of(notification) : Optional.empty();
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }
}
