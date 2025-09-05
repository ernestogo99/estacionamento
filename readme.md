## Api rest para gerenciar estacionamentos de carro e moto

Essa é uma api rest criada para resolver o desafio do link abaixo:

```sh
https://github.com/fcamarasantos/backend-test-java
```

## Estrutura de pastas

```
com.example.estacionamento/
├── configs/          ← Configurações gerais da aplicação (Beans, CORS, etc.)
├── controller/       ← Endpoints REST, coordena a comunicação com services
├── domain/           ← Entidades e objetos de domínio (ex: Carro, Vaga, Ticket)
├── exceptions/       ← Exceções personalizadas para regras de negócio e erros
├── infra/            ← Infraestrutura (persistência, repositórios, integrações)
├── shared/           ← Classes e componentes reutilizáveis entre módulos
├── utils/            ← Funções utilitárias e helpers
└── EstacionamentoApplication.java  ← Ponto de entrada (Spring Boot Application)
```

## Tecnologias utilizadas

- PostgreSQL:Banco de dados relacional escolhido para o sistema
- Java: Linguagem de programação para o back-end
- Spring boot: Framework utilizado para o desenvolvimento de apis

## Destaques do projeto

- Documentação com swagger
- Crud de veiculos
- Crud de estabelecimentos
- Controle de entrada e saída de veículos
- Sumário da quantidade de entrada e saída de veículos
- Sumário da quantidade de entrada e saída de veículos por hora
