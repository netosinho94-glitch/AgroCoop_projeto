# 🌱 AgroCoop - Sistema de Gestão para Cooperativa Agrícola Familiar

## 📖 Sobre o Projeto

O **AgroCoop** é um sistema desenvolvido em **Java** com o objetivo de auxiliar na gestão de uma cooperativa agrícola familiar. A aplicação permite o cadastro de produtores e produtos agrícolas, registro de entregas, cálculo de receitas, geração de relatórios gerenciais e persistência dos dados em banco de dados **MySQL**, proporcionando maior organização e confiabilidade das informações.

O projeto utiliza os principais conceitos da Programação Orientada a Objetos (POO), como encapsulamento, interfaces, polimorfismo, tratamento de exceções e organização em camadas.

---

## 🚀 Funcionalidades

- 👨‍🌾 Cadastro de produtores rurais
- 🌽 Cadastro de produtos agrícolas
- 📦 Registro de entregas de produtos
- 💰 Cálculo automático da receita dos produtores
- 📊 Ranking de produtores por volume entregue
- 🏛️ Consolidação de entregas para programas institucionais (PAA/PNAE)
- ❌ Remoção de produtores cadastrados
- ⚠️ Validação de quantidade entregue através de exceções personalizadas
- 🗄️ Persistência de dados utilizando MySQL

---

## 📋 Menu do Sistema

```text
=== Sistema AgroCoop - Cooperativa Agrícola Familiar ===

1. Cadastrar Produtor
2. Cadastrar Produto Agrícola
3. Registrar Entrega
4. Listar Produtores (e remover)
5. Relatório: Receita por Produtor
6. Relatório: Ranking por Volume
7. Relatório: Consolidação Institucional (PAA/PNAE)
0. Sair
```

---

# 🏗️ Estrutura do Projeto

```
AgroCoop/
│
├── exception/
│   └── QuantidadeInvalidaException.java
│
├── main/
│   └── Main.java
│
├── model/
│   ├── Calculavel.java
│   ├── Entrega.java
│   ├── PrecificacaoPadrao.java
│   ├── PrecificacaoInstitucional.java
│   ├── ProdutoAgricola.java
│   └── Produtor.java
│
└── service/
    └── CooperativaAgricolaService.java
```

---

# 🗄️ Banco de Dados

O projeto utiliza o banco de dados **MySQL** para armazenar as informações dos produtores, produtos agrícolas e entregas realizadas.

O banco foi modelado de forma relacional para garantir a integridade dos dados através de chaves primárias e estrangeiras.

## Banco de Dados

```sql
CREATE DATABASE AgroCoop;

USE AgroCoop;
```

## Tabela: produtor

Responsável por armazenar os dados dos produtores cadastrados.

| Campo | Tipo |
|--------|------|
| id_produtor | INT |
| nome | VARCHAR(100) |
| comunidade | VARCHAR(100) |
| propriedade | VARCHAR(100) |

---

## Tabela: produto_agricola

Armazena todos os produtos cadastrados na cooperativa.

| Campo | Tipo |
|--------|------|
| id_produto | INT |
| nome | VARCHAR(100) |
| unidade_medida | VARCHAR(20) |
| preco_referencia | DECIMAL(10,2) |

---

## Tabela: entrega

Armazena todas as entregas realizadas pelos produtores.

| Campo | Tipo |
|--------|------|
| id_entrega | INT |
| id_produtor | INT |
| id_produto | INT |
| quantidade | DECIMAL(10,2) |
| data_entrega | DATE |
| tipo_precificacao | PADRAO / INSTITUCIONAL |

---

## Relacionamentos

- Um produtor pode realizar várias entregas.
- Um produto agrícola pode estar presente em várias entregas.
- Cada entrega pertence a apenas um produtor.
- Cada entrega referencia apenas um produto agrícola.

```text
Produtor (1)
      │
      │
      ▼
Entrega
      ▲
      │
      │
Produto Agrícola (1)
```

As tabelas utilizam **FOREIGN KEY** para garantir a integridade referencial entre os registros.

---

# 💻 Tecnologias Utilizadas

- Java
- MySQL
- SQL
- Programação Orientada a Objetos (POO)
- Collections Framework
- Tratamento de Exceções
- Interface
- Polimorfismo
- Scanner
- LocalDate

---

# 📚 Conceitos Aplicados

O projeto demonstra diversos conceitos fundamentais da Programação Orientada a Objetos:

- Classes e Objetos
- Encapsulamento
- Interfaces
- Polimorfismo
- Tratamento de Exceções
- Organização em Camadas
- Listas (`ArrayList`)
- Mapas (`HashMap`)
- Ordenação com Collections
- Manipulação de Datas (`LocalDate`)

---

# 🧩 Regras de Negócio

## Cadastro de Produtores

Cada produtor possui:

- Nome
- Comunidade
- Propriedade

---

## Cadastro de Produtos

Cada produto agrícola possui:

- Nome
- Unidade de medida
- Preço de referência

---

## Registro de Entregas

Ao registrar uma entrega são informados:

- Produtor
- Produto
- Quantidade
- Tipo da venda

A quantidade deve ser maior que zero.

Caso contrário, o sistema lança a exceção personalizada:

```java
QuantidadeInvalidaException
```

---

## Precificação

O sistema trabalha com dois tipos de precificação.

### Venda comum

Valor:

```
Quantidade × Preço de Referência
```

### Venda Institucional (PAA/PNAE)

Recebe um incentivo de **8%** sobre o valor da venda.

Fórmula:

```
(Quantidade × Preço) × 1,08
```

---

# 📊 Relatórios Disponíveis

O sistema disponibiliza três relatórios:

### Receita por produtor

Calcula toda a receita obtida por um produtor considerando todas as entregas cadastradas.

---

### Ranking por volume

Ordena os produtores do maior para o menor volume entregue.

---

### Consolidação Institucional

Agrupa todas as entregas por produto, informando a quantidade total entregue.

---

# ⚠️ Tratamento de Exceções

Foi criada uma exceção personalizada:

```java
QuantidadeInvalidaException
```

Ela impede que sejam cadastradas entregas com quantidade menor ou igual a zero.

---

# ▶️ Como Executar

1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/AgroCoop.git
```

2. Abra o projeto em sua IDE Java (IntelliJ IDEA, Eclipse ou VS Code).

3. Configure o banco de dados MySQL executando o script SQL presente no projeto.

4. Compile o projeto.

5. Execute a classe:

```
Main.java
```

6. Utilize o menu no terminal.

---

# 📌 Exemplo de Utilização

```text
Nome do Produtor:
João Silva

Comunidade:
São Pedro

Propriedade:
Sítio Esperança

Produto:
Farinha

Quantidade:
250

Venda Institucional?
S
```

Resultado:

```text
Entrega registrada com sucesso!
```

---

# 📈 Melhorias Futuras

- Interface gráfica (JavaFX)
- API REST
- Login e autenticação de usuários
- Dashboard com gráficos
- Exportação de relatórios em PDF
- Histórico completo de entregas
- Cadastro de cooperativas
- Integração com programas governamentais

---

# 👥 Equipe do Projeto

Este projeto foi desenvolvido pelos seguintes integrantes:

- Camilly Pires
- Dayana Araujo
- Isaque Bezerra
- Natanael dos Santos
- Osvaldo Oliveira
- Ygor Menezes

---

# 👨‍💻 Autor

Projeto desenvolvido como atividade acadêmica para aplicação dos conceitos de Programação Orientada a Objetos em Java e modelagem de banco de dados utilizando MySQL.

---

# 📄 Licença

Este projeto foi desenvolvido para fins educacionais.