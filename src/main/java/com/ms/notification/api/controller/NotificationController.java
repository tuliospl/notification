package com.ms.notification.api.controller;

import com.ms.notification.api.dto.NotificationRequest;
import com.ms.notification.domain.model.Notification;
import com.ms.notification.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequest request) {
        log.info("Recebendo solicitação de notificação: {}", request);
        
        Notification notification = request.toDomain();
        notificationService.sendNotification(notification);
        
        return ResponseEntity.ok().build();
    }
} 