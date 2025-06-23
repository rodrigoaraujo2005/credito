# Sistema de Crédito

Sistema para consulta e gerenciamento de créditos constituídos, composto por uma API REST (backend) e uma interface de usuário (frontend).

## Estrutura do Projeto

O projeto está organizado nos seguintes diretórios:

- **backend**: API REST desenvolvida com Spring Boot
- **frontend**: Interface de usuário desenvolvida com Angular
- **database**: Scripts de inicialização do banco de dados
- **docker-compose.yml**: Configuração para execução de toda a infraestrutura

## Requisitos

Para executar este projeto, você precisa ter instalado:

- Docker
- Docker Compose

## Infraestrutura

O projeto utiliza os seguintes componentes de infraestrutura, configurados no arquivo `docker-compose.yml`:

### PostgreSQL

- **Imagem**: postgres:13
- **Porta**: 5432
- **Banco de dados**: creditdb
- **Usuário**: credituser
- **Senha**: creditpass
- **Volume**: Dados persistidos em volume Docker
- **Inicialização**: Script SQL em `./database/init.sql`

### Kafka

- **Imagem**: confluentinc/cp-kafka:7.3.0
- **Porta**: 9092 (interna), 29092 (externa)
- **Dependência**: Zookeeper

### Zookeeper (para Kafka)

- **Imagem**: confluentinc/cp-zookeeper:7.3.0
- **Porta**: 2181

## Backend (API REST)

O backend é uma aplicação Spring Boot que fornece APIs para consulta e gerenciamento de créditos.

### Tecnologias

- Spring Boot
- Spring Data JPA
- Spring Web (REST)
- Kafka para mensageria
- PostgreSQL como banco de dados

### Configuração

A aplicação pode ser configurada através de variáveis de ambiente:

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| SPRING_DATASOURCE_URL | URL de conexão com o banco de dados | jdbc:postgresql://postgres:5432/creditdb |
| SPRING_DATASOURCE_USERNAME | Usuário do banco de dados | credituser |
| SPRING_DATASOURCE_PASSWORD | Senha do banco de dados | creditpass |
| SPRING_KAFKA_BOOTSTRAP_SERVERS | Endereço do servidor Kafka | kafka:9092 |
| SERVER_PORT | Porta da aplicação | 8080 |

## Frontend (Interface de Usuário)

O frontend é uma aplicação Angular que fornece uma interface amigável para interação com a API.

### Tecnologias

- Angular 14
- Bootstrap 5.2.0
- Nginx (para servir a aplicação em produção)

### Configuração

A aplicação frontend é servida através do Nginx na porta 80 e se comunica com o backend na porta 8080.

## Executando o Projeto

### Usando Docker Compose (Recomendado)

O método mais simples para executar a aplicação completa é usando Docker Compose:

```bash
# Clone o repositório (se ainda não tiver feito)
git clone <url-do-repositorio>
cd credito

# Inicie todos os serviços
docker-compose up -d

# Para visualizar os logs
docker-compose logs -f

# Para parar todos os serviços
docker-compose down
```

Após iniciar os serviços:
- Frontend estará disponível em: http://localhost:80
- Backend estará disponível em: http://localhost:8080

### Executando Componentes Individualmente

Cada componente (backend e frontend) possui seu próprio Dockerfile e pode ser executado individualmente. Consulte os READMEs específicos em cada diretório para mais detalhes.

## Desenvolvimento

Para desenvolvimento local:

1. Configure as instâncias locais de PostgreSQL e Kafka ou use o docker-compose para iniciá-las
2. Execute o backend seguindo as instruções no diretório `backend`
3. Execute o frontend seguindo as instruções no diretório `frontend`

## Licença

Este projeto está licenciado sob os termos da licença MIT.
