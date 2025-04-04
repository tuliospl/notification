package com.ms.notification.infrastructure.persistence;

import com.ms.notification.domain.model.Notification;
import com.ms.notification.domain.repository.NotificationRepository;
import com.ms.notification.infrastructure.persistence.entity.NotificationEntity;
import com.ms.notification.infrastructure.persistence.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaNotificationRepository implements NotificationRepository {

    private final SpringDataNotificationRepository repository;
    private final NotificationMapper mapper;

    @Override
    public Notification save(Notification notification) {
        NotificationEntity entity = mapper.toEntity(notification);
        entity = repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Notification> saveAll(List<Notification> notifications) {
        List<NotificationEntity> entities = notifications.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        
        entities = repository.saveAll(entities);
        
        return entities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
} 