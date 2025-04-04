# Sistema de Notificações

## 📋 Descrição
Sistema genérico de notificações que permite o envio de diferentes tipos de mensagens (email, SMS, etc.) baseado em templates recebidos através de tópicos de mensageria.

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Mail
- PostgreSQL
- Lombok
- Maven

## 🏗️ Arquitetura

### Componentes Principais
- **API REST**: Endpoints para gerenciamento de notificações
- **Serviço de Notificações**: Core do sistema responsável pelo processamento e envio
- **Templates**: Sistema flexível de templates para diferentes tipos de mensagens
- **Persistência**: Armazenamento de histórico e configurações no PostgreSQL

### Fluxo de Funcionamento
1. Recebimento de mensagem do tópico
2. Processamento do template
3. Seleção do canal de envio (email, SMS, etc.)
4. Envio da notificação
5. Registro do histórico

## 🛠️ Configuração do Ambiente

### Pré-requisitos
- Java 17
- Maven
- PostgreSQL

### Configuração do Banco de Dados
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/notification_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
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
git clone [url-do-repositorio]
```

2. Configure as variáveis de ambiente ou application.properties

3. Execute o projeto
```bash
mvn spring-boot:run
```

## 📝 Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── com/ms/notification/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── NotificationApplication.java
│   └── resources/
│       └── application.properties
```

## 🤝 Contribuição
1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request
