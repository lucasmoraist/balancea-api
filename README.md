# Balancea - Backend
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## Descrição
Balancea é uma aplicação de controle de orçamento familiar que permite aos usuários cadastrarem suas receitas e despesas mensais, com categorização das despesas e geração de relatórios financeiros detalhados. A aplicação é ideal para aqueles que desejam ter uma visão clara sobre suas finanças, auxiliando no controle e planejamento de gastos.

## Funcionalidades
1. Cadastro de Receitas
   - Valor: Valor da receita.
   - Descrição: Descrição da receita.
   - Data: Data em que a receita foi recebida.
````json
{
  "valor": 1000,
  "descricao": "Salário",
  "data": "03/10/2024"
}
````
2. Cadastro de Despesas
   - Valor: Valor da despesa.
   - Descrição: Descrição da despesa.
   - Categoria: Categoria da despesa (Ex.: Alimentação, Transporte).
   - Data: Data em que a despesa foi realizada.
````json
{
  "valor": 200,
  "descricao": "Jantar",
  "categoria": "ALIMENTACAO",
  "data": "03/10/2024"
}

````
3. Listagem de Receitas/Despesas
- Exibe uma listagem completa das receitas e despesas cadastradas, podendo listar por:
    - Ano e Mês
    - Paginação
    - Query
    - Por ID
4. Relatório Mensal
   - Gera um relatório detalhado com:
   - Total de receitas.
   - Total de despesas.
   - Saldo total.
   - Total de despesas por categoria.
````json
{
    "totalIncome": 1000.0,
    "totalExpense": 500.0,
    "totalBalance": 500.0,
    "totalExpenseByCategory": [
        {
            "category": "ALIMENTACAO",
            "total": 200.0
        },
        {
            "category": "TRANSPORTE",
            "total": 300.0
        }
    ]
}
````
5. Autenticação e Autorização
   - Autenticação JWT para proteger as rotas e assegurar que apenas usuários autenticados possam acessar os dados financeiros.
6. Documentação
   - Utilização do Swagger para documentação da API.
   - Acesse a documentação na sua máquina local via `http://localhost:8080/`

## Instruções de Instalação
### Pré-requisitos
- Java 17 ou superior
- IDE (Eclipse, IntelliJ, VSCode)
- Maven 3.2.5 ou superior
- Docker
### Etapas
1. Clone o repositório na sua máquina:
```bash
git clone https://github.com/lucasmoraist/balancea-api.git
```
2. Acesse o diretório do projeto:
```bash
cd balancea-api
```
3. Crie um arquivo `.env` no diretório raiz do projeto e adicione as seguintes variáveis:
```
POSTGRES_USER=seu_usuario
POSTGRES_DB=seu_banco
POSTGRES_PASSWORD=sua_senha
POSTGRES_PORT=5432
JWT_SECRET=sua_chave_jwt
```
4. Execute o Docker Compose para iniciar o banco de dados e a aplicação:
```bash
docker-compose up -d
```
5. Acesse a aplicação na sua máquina local via `http://localhost:8080`

## Instruções de Uso
- Com o projeto em execução, utilize uma ferramenta como Postman ou Insomnia para testar a API.
- Acesse o Swagger para visualizar e testar os endpoints disponíveis.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests com melhorias, correções de bugs ou novos recursos.

## Contatos
<a href = "mailto:seu-email@gmail.com">
  <img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank" alt="">
</a>
<a href="https://www.linkedin.com/in/seu-linkedin/" target="_blank">
  <img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank" alt="">
</a>
