package com.ms.notification.infrastructure.service;

import com.ms.notification.domain.enums.NotificationChannel;
import com.ms.notification.domain.model.Notification;
import com.ms.notification.domain.service.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailNotificationStrategy implements NotificationStrategy {

    private final JavaMailSender mailSender;

    @Override
    public void send(Notification notification) {
        log.info("Enviando email para: {}", notification.getRecipients());
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(notification.getRecipients().toArray(new String[0]));
        mailMessage.setFrom(notification.getSender());
        mailMessage.setSubject(notification.getTitle());
        mailMessage.setText(notification.getBody());
        
        mailSender.send(mailMessage);
        log.info("Email enviado com sucesso para: {}", notification.getRecipients());
    }

    @Override
    public boolean supports(Notification notification) {
        return NotificationChannel.EMAIL.equals(notification.getChannel());
    }
} 