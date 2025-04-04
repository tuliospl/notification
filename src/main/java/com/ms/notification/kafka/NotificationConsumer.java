package com.ms.notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.notification.dto.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${notification.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            log.info("Mensagem recebida do Kafka: {}", message);
            
            NotificationMessage notificationMessage = objectMapper.readValue(message, NotificationMessage.class);
            log.info("Mensagem convertida para objeto: {}", notificationMessage);
        } catch (Exception e) {
            log.error("Erro ao processar mensagem do Kafka: {}", message, e);
        }
    }
}
