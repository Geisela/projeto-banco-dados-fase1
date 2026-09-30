# Modelo Conceitual - Sistema de Cinema

```mermaid
erDiagram
    FUNCIONARIO {
        int id PK
        string nome
        string cpf
        string cargo
        string telefone
    }

    FILME {
        int id PK
        string titulo
        string genero
        int duracao_minutos
        string classificacao_indicativa
        string sinopse
    }

    SALA {
        int id PK
        int numero
        int capacidade
        string tipo_sala
    }

    CLIENTE {
        int id PK
        string nome
        string cpf
        string email
        string telefone
    }

    SESSAO {
        int id PK
        datetime data_hora
        decimal preco_ingresso
    }

    INGRESSO {
        int id PK
        string assento
        string forma_pagamento
        decimal valor_pago
        datetime data_compra
    }

    FILME       ||--o{ SESSAO   : "e exibido em"
    SALA        ||--o{ SESSAO   : "recebe"
    FUNCIONARIO ||--o{ SESSAO   : "agenda"
    SESSAO      ||--o{ INGRESSO : "tem"
    CLIENTE     ||--o{ INGRESSO : "compra"
```

## Leitura das cardinalidades

- Um **filme** pode ser exibido em várias **sessões**; cada sessão exibe um único filme.
- Uma **sala** recebe várias **sessões** (em horários diferentes); cada sessão ocorre em uma única sala.
- Um **funcionário** agenda várias **sessões**; cada sessão é agendada por um funcionário.
- Uma **sessão** tem vários **ingressos** (um por assento); cada ingresso pertence a uma sessão.
- Um **cliente** compra vários **ingressos**; cada ingresso é comprado por um cliente.

## Observações

- **Sessão** e **Ingresso** são entidades associativas: Sessão liga Filme, Sala e Funcionário; Ingresso liga Sessão e Cliente.
- Por ser conceitual, o diagrama não traz chaves estrangeiras nem tipos específicos do SQLite. Os identificadores aparecem só como `id`.
- Restrições de negócio:
  - Uma sala não pode ter duas sessões no mesmo horário.
  - Um assento não pode ser vendido duas vezes na mesma sessão.
  - CPF é único para funcionário e cliente.
