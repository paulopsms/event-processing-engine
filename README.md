# event-processing-engine

### Processador de eventos

Projeto desenvolvido para construção de um módulo de processamento de eventos financeiros utilizando Java 8, Spring Boot
e princípios de Clean Architecture.

O objetivo do sistema é receber eventos financeiros provenientes de múltiplas fontes,
processá-los de forma determinística, lidar com duplicidades e conflitos, e gerar um consolidado financeiro por conta.


<h1 align="center" style="font-weight: bold;">💻 Event Processing Engine 💻</h1>

![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Amazon S3](https://img.shields.io/badge/Amazon%20S3-FF9900?style=for-the-badge&logo=amazons3&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)


<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a>
</p>

<p align="center">
  <b>Projeto desenvolvido para construção de um módulo de processamento de eventos financeiros utilizando Java 8, Spring Boot
e princípios de Clean Architecture. O objetivo do sistema é receber eventos financeiros provenientes de múltiplas fontes,
processá-los de forma determinística, lidar com duplicidades e conflitos, e gerar um consolidado financeiro por conta.</b>
</p>

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

For this project to run, you'll need to have Docker and docker-compose installed.
Para executar este projeto, você precisará ter instalado o Java 8 e o Maven. 
O Projeto utiliza Banco de Dados H2, Spring-Boot 2.7.18 e JUnit5.

- [Java 8](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

<h3>Cloning</h3>

Clonando o repositório para sua máquina.

```bash
git clone https://github.com/paulopsms/event-processing-engine.git
```

<h3>Starting</h3>

Para executar a aplicação, basta aessar seu diretório e executar:

```bash
mvn spring-boot:run
``````

<h2 id="routes">📍 API Endpoints</h2>

Aqui estão listados as principais rotas da aplicação e como executá-las.

​

| route                       | description                                                                                       |
|-----------------------------|---------------------------------------------------------------------------------------------------|
| <kbd>POST /events/ingest>   | Processa um único evento. [Ver detalhes](#post-single-event)                                      |
| <kbd>POST /queue/batch>     | Processa um lote de eventos. [Ver detalhes](#post-batch-events)                                   |
| <kbd>GET /events/issues>    | Lista todos os problemas de todos os eventos. [Ver detalhes](#get-event-issues)                   |
| <kbd>GET /accounts/summary> | Lista o saldo e outras informações das contas processadas. [Ver detalhes](#get-account-summaries) |

<h3 id="post-single-event">POST /events/ingest</h3>

**REQUEST**

Para este teste, você pode usar o arquivo: [teste_single_processing.json](postman/teste_single_processing.json)
```json
{
  "eventId": "EVT-0001",
  "accountId": "ACC-001",
  "occurredAt":  "2026-02-01T10:00:00Z",
  "type": "CREDIT",
  "amount": 10.00
}
```

**RESPONSE**

```
Status 202 Accepted (Empty body)
```

<h3 id="post-batch-events">POST /queue/batch</h3>

**REQUEST**

Para o seu teste, você pode usar o arquivo: [teste_batch_processing.json](postman/teste_batch_processing.json)[teste_single_processing.json](postman/teste_single_processing.json)

```json
[
  {
    "eventId": "EVT-0001",
    "accountId": "ACC-001",
    "occurredAt": "2026-02-01T10:00:00Z",
    "type": "CREDIT",
    "amount": 10.00
  },
  {
    "eventId": "EVT-0002",
    "accountId": "ACC-001",
    "occurredAt": "2026-02-01T10:15:00Z",
    "type": "CREDIT",
    "amount": 5.00
  }
]
```

**RESPONSE**

```
Status 202 Accepted (Empty body)
```

<h3 id="get-event-issues">GET /events/issues</h3>

**REQUEST**

```
Empty Body
```

**RESPONSE**

```json
[
  {
    "eventId": "EVT-0001",
    "type": "DUPLICATE",
    "detectedAt": "2026-03-09T20:32:19.15"
  },
  {
    "eventId": "EVT-0001",
    "type": "CONFLICT",
    "detectedAt": "2026-03-09T20:43:42.183"
  }
]
```

<h3 id="get-account-summaries">GET /accounts/summary</h3>

**REQUEST**

```
Empty Body
```

**RESPONSE**

```json
[
  {
    "accountId": "ACC-001",
    "balance": 10.00,
    "totalCredits": 10.00,
    "totalDebits": 0.00,
    "validEvents": 1,
    "duplicateEvents": 0,
    "conflictEvents": 0
  },
  {
    "accountId": "ACC-002",
    "balance": 16.00,
    "totalCredits": 20.00,
    "totalDebits": 4.00,
    "validEvents": 2,
    "duplicateEvents": 1,
    "conflictEvents": 0
  }
]
```



