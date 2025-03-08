package com.br.notification.controller.DTO;

public record NotificationRequestDTO(
    String id,
    String message,
    String sender,
    String receiver,
    String createdAt
) {}
