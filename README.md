# Sistema de Notificações

## 📋 Descrição
Sistema genérico de notificações que permite o envio de diferentes tipos de mensagens (email, SMS, etc.) baseado em templates recebidos através de tópicos de mensageria.

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Mail
- Spring Kafka
- PostgreSQL
- Apache Kafka
- Kafdrop (UI para gerenciamento do Kafka)
- Lombok
- Maven

## 🏗️ Arquitetura

### Componentes Principais
- **API REST**: Endpoints para gerenciamento de notificações
- **Serviço de Notificações**: Core do sistema responsável pelo processamento e envio
- **Kafka**: Sistema de mensageria para recebimento de notificações
- **Templates**: Sistema flexível de templates para diferentes tipos de mensagens
- **Persistência**: Armazenamento de histórico e configurações no PostgreSQL

### Fluxo de Funcionamento
1. Recebimento de mensagem do tópico Kafka
2. Processamento do template
3. Seleção do canal de envio (email, SMS, etc.)
4. Envio da notificação
5. Registro do histórico

## 🛠️ Configuração do Ambiente

### Pré-requisitos
- Java 17
- Maven
- Docker e Docker Compose
- PostgreSQL

### Configuração do Banco de Dados
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/notification_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Configuração do Kafka
```properties
spring.kafka.bootstrap-servers=localhost:29092
spring.kafka.consumer.group-id=notification-group
notification.topic.name=notification-topic
```

### Configuração de Email
```properties
spring.mail.host=smtp.seu_provedor.com
spring.mail.port=587
spring.mail.username=seu_email
spring.mail.password=sua_senha
```

## 🚀 Como Executar

1. Clone o repositório
```bash
git clone git@github.com:tuliospl/notification.git
```

2. Inicie os serviços do Kafka e Kafdrop
```bash
docker-compose up -d
```

3. Configure as variáveis de ambiente ou application.properties

4. Execute o projeto
```bash
mvn spring-boot:run
```

5. Acesse o Kafdrop para gerenciar tópicos e mensagens
```
http://localhost:9000
```

## 📝 Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── com/ms/notification/
│   │       ├── config/          # Configurações do sistema
│   │       ├── controller/      # Controladores REST
│   │       ├── kafka/           # Consumidores e produtores Kafka
│   │       ├── model/           # Entidades JPA
│   │       ├── repository/      # Repositórios JPA
│   │       ├── service/         # Lógica de negócio
│   │       ├── dto/             # Objetos de transferência de dados
│   │       ├── enums/           # Enumeradores
│   │       └── NotificationApplication.java
│   └── resources/
│       └── application.properties
```

## 🔄 Fluxo de Mensagens
1. Mensagem é enviada para o tópico `notification-topic`
2. `NotificationConsumer` recebe e processa a mensagem
3. Mensagem é convertida para objeto `NotificationMessage`
4. Sistema processa a notificação de acordo com o canal especificado
5. Resultado é persistido no banco de dados

## 🤝 Contribuição
1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request
