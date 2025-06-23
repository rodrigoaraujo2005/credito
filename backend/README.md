# Credito API

API para a consulta de créditos constituídos.

## Requisitos

Para executar este projeto, você precisa ter instalado:

- Docker
- Docker Compose

## Executando com Docker

### Usando Docker Compose (Recomendado)

O método mais simples para executar a aplicação é usando Docker Compose, que iniciará a aplicação junto com PostgreSQL e Kafka:

```bash
# Clone o repositório (se ainda não tiver feito)
git clone <url-do-repositorio>
cd credito/backend

# Inicie todos os serviços
docker-compose up -d

# Para visualizar os logs
docker-compose logs -f

# Para parar todos os serviços
docker-compose down
```

A aplicação estará disponível em: http://localhost:8080

### Construindo e Executando Apenas a Aplicação

Se você já possui PostgreSQL e Kafka em execução, pode construir e executar apenas a aplicação:

```bash
# Construir a imagem Docker
docker build -t credito-api .

# Executar o container
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<seu-host-postgres>:5432/creditdb \
  -e SPRING_DATASOURCE_USERNAME=<seu-usuario> \
  -e SPRING_DATASOURCE_PASSWORD=<sua-senha> \
  -e SPRING_KAFKA_BOOTSTRAP_SERVERS=<seu-host-kafka>:9092 \
  --name credito-api \
  credito-api
```

## Variáveis de Ambiente

A aplicação pode ser configurada através das seguintes variáveis de ambiente:

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| SPRING_DATASOURCE_URL | URL de conexão com o banco de dados | jdbc:postgresql://postgres:5432/creditdb |
| SPRING_DATASOURCE_USERNAME | Usuário do banco de dados | credituser |
| SPRING_DATASOURCE_PASSWORD | Senha do banco de dados | creditpass |
| SPRING_KAFKA_BOOTSTRAP_SERVERS | Endereço do servidor Kafka | kafka:9092 |
| SERVER_PORT | Porta da aplicação | 8080 |

## Estrutura do Projeto

Este projeto é uma aplicação Spring Boot que utiliza:

- Spring Data JPA para persistência
- Spring Web para APIs REST
- Kafka para mensageria
- PostgreSQL como banco de dados

## Desenvolvimento

Para desenvolvimento local sem Docker, configure as propriedades em `src/main/resources/application.properties` para apontar para suas instâncias locais de PostgreSQL e Kafka.