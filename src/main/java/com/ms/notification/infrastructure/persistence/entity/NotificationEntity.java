package com.ms.notification.infrastructure.persistence.entity;

import com.ms.notification.domain.model.NotificationChannel;
import com.ms.notification.domain.model.NotificationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "notifications")
public class NotificationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    @CollectionTable(name = "notification_recipients", 
                    joinColumns = @JoinColumn(name = "notification_id"))
    @Column(name = "recipient", nullable = false)
    private List<String> recipients;
    
    @Column(nullable = false)
    private String sender;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationChannel channel;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;
    
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
    
    @Column(name = "error_message")
    private String errorMessage;
} 