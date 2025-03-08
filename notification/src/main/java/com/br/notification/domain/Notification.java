package com.br.notification.domain;

import com.br.notification.controller.DTO.NotificationRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;

import static jakarta.persistence.GenerationType.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity(name = "notification")
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = UUID)
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private String createdAt;

    public Notification(NotificationRequestDTO notificationRequestDTO) {
        this.message = notificationRequestDTO.message();
        this.sender = notificationRequestDTO.sender();
        this.receiver = notificationRequestDTO.receiver();
        this.createdAt = LocalDate.now().toString();
    }
}
