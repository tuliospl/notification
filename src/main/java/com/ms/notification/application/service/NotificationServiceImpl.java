package com.ms.notification.application.service;

import com.ms.notification.domain.model.Notification;
import com.ms.notification.domain.repository.NotificationRepository;
import com.ms.notification.domain.service.NotificationService;
import com.ms.notification.domain.service.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final List<NotificationStrategy> strategies;

    @Override
    @Transactional
    public void sendNotification(Notification notification) {
        log.info("Processando notificação para o canal: {}", notification.getChannel());
        
        List<String> recipients = notification.getRecipients();
        log.info("Total de destinatários: {}", recipients.size());
        
        recipients.forEach(recipient -> {
            Notification individualNotification = new Notification(
                recipient,
                notification.getSender(),
                notification.getTitle(),
                notification.getBody(),
                notification.getChannel(),
                notification.getStatus()
            );
            
            strategies.stream()
                    .filter(strategy -> strategy.supports(individualNotification))
                    .findFirst()
                    .ifPresentOrElse(
                            strategy -> {
                                try {
                                    strategy.send(individualNotification);
                                    individualNotification.markAsSent();
                                } catch (Exception e) {
                                    log.error("Erro ao enviar notificação para {}: {}", recipient, e.getMessage());
                                    individualNotification.markAsError(e.getMessage());
                                }
                                notificationRepository.save(individualNotification);
                            },
                            () -> {
                                log.error("Nenhuma estratégia encontrada para o canal: {}", individualNotification.getChannel());
                                individualNotification.markAsError("Canal não suportado");
                                notificationRepository.save(individualNotification);
                            }
                    );
        });
    }
} 