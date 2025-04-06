
# Sale Service 🚗💰

Este microserviço faz parte do ecossistema do projeto Tech Challenge e é responsável pela **gestão de vendas de veículos**.  
Ele permite criar vendas, consultar e atualizar o status das vendas, integrando-se com os demais serviços (Vehicle Service e Payment Service).

## Features

✅ Criação de vendas  
✅ Consulta de todas as vendas  
✅ Atualização de status da venda (CONCLUIDO ou CANCELADO)  
✅ Integração com o Payment Service para criação de pagamentos  
✅ Integração com o Vehicle Service para atualização de status do veículo vendido  
✅ Clean Architecture  
✅ Docker e Kubernetes Ready  
✅ Documentação OpenAPI / Swagger (em breve!)

## Tecnologias

- Java 17
- Spring Boot 3.x
- Gradle
- PostgreSQL (banco de dados)
- Docker
- Kubernetes
- REST API
- Clean Architecture
- Feign Client para comunicação entre serviços

## Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── com.fiap.saleservice
│   │       ├── application
│   │       │   └── usecase (casos de uso da aplicação)
│   │       ├── domain
│   │       │   └── entity (entidades de domínio)
│   │       ├── infrastructure
│   │       │   ├── controller (controllers REST)
│   │       │   ├── gateway (implementações dos gateways)
│   │       │   ├── repository (JPA repositories)
│   │       │   └── client (feign/rest clients)
│   └── resources
│       └── application.yml
└── test
    └── java
```

## Como executar localmente 🚀

### Pré-requisitos

- Java 17+
- Docker
- PostgreSQL rodando (ou utilize o `docker-compose` abaixo)

### Banco de dados

Sugerido para o Sale Service:
- Banco: `sales_db`
- Usuário: `postgres`
- Senha: `postgres`

Você pode subir usando Docker:

```bash
docker run --name sales-db -e POSTGRES_DB=sales_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5433:5432 -d postgres
```

> ⚠️ Atenção: usamos a porta `5433` para evitar conflito com outros serviços.

### Build e run local

```bash
./gradlew clean build
./gradlew bootRun
```

A aplicação irá subir na porta **8082**.

### Endpoints disponíveis

| Método | Endpoint                       | Descrição                          |
|--------|--------------------------------|-----------------------------------|
| POST   | `/sales`                       | Cria uma nova venda               |
| GET    | `/sales`                       | Lista todas as vendas             |
| PUT    | `/sales/{id}/concluir`         | Conclui uma venda                 |
| PUT    | `/sales/{id}/cancelar`         | Cancela uma venda                 |

## Kubernetes 📦

Já existe um arquivo base para deploy no Kubernetes.

## Observações

- O serviço se comunica com o `vehicle-service` e `payment-service`, certifique-se que ambos estejam ativos.
- O `payment-service` automaticamente cria o pagamento quando a venda é criada.

---

© Tech Challenge - 2025
