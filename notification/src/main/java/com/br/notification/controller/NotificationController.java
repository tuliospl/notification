package com.br.notification.controller;

import com.br.notification.controller.DTO.NotificationRequestDTO;
import com.br.notification.domain.Notification;
import com.br.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody NotificationRequestDTO notificationRequest) {
        return notificationService.createNotification(notificationRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notification>> getNotification(@PathVariable String id) {
        Optional<Notification> notification = notificationService.getNotification(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
