# Serviço de Notificações

Serviço responsável por gerenciar e enviar notificações através de diferentes canais (email, SMS, etc).

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Mail
- Spring Kafka
- Apache Kafka
- Kafdrop
- PostgreSQL
- Docker
- Docker Compose
- Maven

## Arquitetura

O projeto segue os princípios da Arquitetura Hexagonal (Ports and Adapters):

### Camadas

1. **Domain** (Centro da aplicação)
   - Contém as entidades de domínio
   - Define os ports (interfaces) para adaptadores
   - Regras de negócio core
   - Independente de frameworks
   - Pacotes:
     - `model`: Entidades e enums
     - `service`: Interfaces de serviço
     - `repository`: Interfaces de repositório

2. **Application**
   - Implementa os casos de uso
   - Orquestra o fluxo entre domain e infrastructure
   - Implementa os handlers de mensagens
   - Pacotes:
     - `service`: Implementações dos serviços

3. **Infrastructure**
   - Implementa os adaptadores
   - Persistência (JPA)
   - Mensageria (Kafka)
   - Email (Spring Mail)
   - Pacotes:
     - `persistence`: JPA e mappers
     - `kafka`: Produtores e consumidores
     - `service`: Estratégias de notificação
     - `config`: Configurações

4. **API**
   - Controladores REST
   - DTOs
   - Endpoints públicos
   - Pacotes:
     - `controller`: Endpoints REST
     - `dto`: Objetos de transferência

## Estrutura do Projeto

```
src/main/java/com/ms/notification/
├── api/
│   ├── controller/
│   │   └── NotificationController.java
│   └── dto/
│       └── NotificationRequest.java
├── domain/
│   ├── model/
│   │   ├── Notification.java
│   │   ├── NotificationChannel.java
│   │   └── NotificationStatus.java
│   ├── service/
│   │   └── NotificationService.java
│   └── repository/
│       └── NotificationRepository.java
├── application/
│   └── service/
│       └── NotificationServiceImpl.java
└── infrastructure/
    ├── persistence/
    │   ├── entity/
    │   │   └── NotificationEntity.java
    │   ├── repository/
    │   │   └── JpaNotificationRepository.java
    │   └── mapper/
    │       └── NotificationMapper.java
    ├── kafka/
    │   ├── NotificationConsumer.java
    │   └── NotificationProducer.java
    ├── service/
    │   └── EmailNotificationStrategy.java
    └── config/
        └── KafkaConfig.java
```

## Endpoints da API

### Enviar Notificação
```
POST /api/notifications
```

Payload:
```json
{
  "recipients": ["email@exemplo.com"],
  "sender": "sistema@exemplo.com",
  "title": "Título da Notificação",
  "body": "Conteúdo da notificação",
  "channel": "EMAIL"
}
```

## Configuração

### Kafka
```properties
spring.kafka.bootstrap-servers=localhost:29092
spring.kafka.consumer.group-id=notification-group
notification.topic.name=notification-topic
```

### Email
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seu-email@gmail.com
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Banco de Dados
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/notification_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Executando o Projeto

1. Inicie os serviços com Docker Compose:
```bash
docker-compose up -d
```

2. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

## Fluxo de Mensagens

1. Cliente envia requisição para a API
2. API converte DTO para objeto de domínio
3. Serviço processa a notificação
4. Estratégia apropriada é selecionada baseada no canal
5. Notificação é enviada e status é atualizado
6. Histórico é persistido no banco de dados

## Canais de Notificação

- **EMAIL**: Envio de emails
- **SMS**: Envio de mensagens SMS
- **PUSH**: Notificações push

## Status da Notificação

- **PENDING**: Aguardando envio
- **SENT**: Enviada com sucesso
- **ERROR**: Erro no envio
