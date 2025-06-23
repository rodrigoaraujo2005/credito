# Credito Frontend

Interface de usuário para o sistema de consulta e gerenciamento de créditos constituídos.

## Visão Geral

Esta aplicação frontend foi desenvolvida com Angular e fornece uma interface amigável para interação com a API de crédito. Ela permite aos usuários consultar e gerenciar créditos de forma intuitiva.

## Tecnologias Utilizadas

- **Angular 14**: Framework para desenvolvimento da aplicação
- **Bootstrap 5.2.0**: Framework CSS para estilização
- **RxJS**: Biblioteca para programação reativa
- **Nginx**: Servidor web para servir a aplicação em produção

## Requisitos

Para executar este projeto, você precisa ter instalado:

- Docker
- Docker Compose

Alternativamente, para desenvolvimento local:
- Node.js 14+
- npm 6+
- Angular CLI 14

## Executando com Docker

### Usando Docker Compose (Recomendado)

O método mais simples para executar a aplicação frontend é usando Docker Compose:

```bash
# Clone o repositório (se ainda não tiver feito)
git clone <url-do-repositorio>
cd credito/frontend

# Inicie o serviço frontend
docker-compose up -d

# Para visualizar os logs
docker-compose logs -f

# Para parar o serviço
docker-compose down
```

A aplicação estará disponível em: http://localhost:80

### Construindo e Executando Apenas o Container

Se você preferir construir e executar apenas o container Docker:

```bash
# Construir a imagem Docker
docker build -t credito-frontend .

# Executar o container
docker run -d -p 80:80 --name credito-frontend credito-frontend
```

## Variáveis de Ambiente

A aplicação pode ser configurada através das seguintes variáveis de ambiente:

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| API_URL | URL da API backend | http://backend:8080 |

## Desenvolvimento Local

Para desenvolvimento local sem Docker:

```bash
# Instalar dependências
npm install

# Iniciar servidor de desenvolvimento
npm start
```

A aplicação estará disponível em: http://localhost:4200

### Comandos Úteis

- `npm start`: Inicia o servidor de desenvolvimento
- `npm run build`: Compila a aplicação para produção
- `npm test`: Executa os testes unitários
- `npm run lint`: Executa a verificação de código
- `npm run e2e`: Executa os testes end-to-end

## Estrutura do Projeto

Esta aplicação segue a estrutura padrão de projetos Angular:

- **src/app**: Componentes, serviços e módulos da aplicação
- **src/assets**: Recursos estáticos (imagens, fontes, etc.)
- **src/environments**: Configurações de ambiente (desenvolvimento, produção)

## Integração com Backend

A aplicação frontend se comunica com o backend através de APIs REST. Por padrão, ela espera que o backend esteja disponível em `http://backend:8080` quando executada em Docker, ou em `http://localhost:8080` para desenvolvimento local.

## Construção para Produção

Para construir a aplicação para produção:

```bash
npm run build
```

Os arquivos compilados serão gerados no diretório `dist/`.