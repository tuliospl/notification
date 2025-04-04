package com.ms.notification.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.notification.api.dto.NotificationRequest;
import com.ms.notification.domain.model.Notification;
import com.ms.notification.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @Value("${notification.topic.name}")
    private String topicName;

    @KafkaListener(topics = "${notification.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            log.info("Recebendo mensagem do tópico {}: {}", topicName, message);
            
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            Notification notification = request.toDomain();
            
            notificationService.sendNotification(notification);
            
            log.info("Notificação processada com sucesso: {}", notification);
        } catch (Exception e) {
            log.error("Erro ao processar mensagem do Kafka: {}", e.getMessage(), e);
        }
    }
} 