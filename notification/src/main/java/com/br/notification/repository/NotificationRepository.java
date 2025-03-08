package com.br.notification.repository;

import com.br.notification.domain.Notification;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Nonnull
    Optional<Notification> findById(@Nonnull String id);
}
