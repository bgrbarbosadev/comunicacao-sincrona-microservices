Nome da sua API

O sistema de agendamento de eventos foi desenvolvido na tecnologia de microserviços. O mesmo está dividido de acordo com os projetos abaixo:

  ms-discovery: Responsável por ser o service registry da aplicação. Ao inicializar, os microserviços se registram no serviço para que possam ser mapeados
  e gerado um LoadBalance quando um ou mais containers sejam levantados.

  ms-gateway: Responsável por rotear as requisições dos microserviçoes, direcionando seu tráfego para o serviço correspondente a requisição.

  ms-eventos: Responsável pelo controle do cadastro de eventos da aplicação. Uma conexão síncrona entre ele e o microserviço de agendamento, garante com que
  os dados de um evento seja lançado na base do agendamento.

  ms-convidados: Responsável pelo controle do cadastro de convidados da aplicação. Uma conexão síncrona entre ele e o microserviço de agendamento, garante com que
  os dados de um convidado seja lançado na base do agendamento.

  ms-agendamento: Responsável pelo controle dos agendamentos das participações nos eventos cadastrados na aplicação. Após o cadastro, o serviço envia um email para o convidado
  informando sobre o status da inscrição.

🚀 Começando
Estas instruções permitirão que você tenha uma cópia do projeto em execução em sua máquina local para fins de desenvolvimento e teste.

📋 Pré-requisitos
O que você precisa para rodar a API?

Docker

Docker Compose

git/bash

🔧 Instalação e Execução
Siga estes passos para ter o projeto rodando localmente.

1) Clone o repositório:

Bash:

git clone https://github.com/bgrbarbosadev/comunicacao-sincrona-microservices.git
cd comunicacao-sincrona-microservices

2) Crie um arquivo de variáveis de ambiente com base no exemplo:

HOST_MAIL: Para armazenar os do servidor de email a ser utilizado.
HOST_USERNAME: Usuário utilizado no servidor de email
PASSWORD: senha do usuário
PORT: Porta utilzada pelo servidor de email, Ex: 587 usada no servidor gmail

3) Suba os contêineres usando Docker Compose:

docker-compose up -d --build
Isso irá construir as imagens do Docker, criar os contêineres e iniciá-los em segundo plano.

A API estará acessível em http://localhost:PORTA_DA_SUA_API conforme a documentação de cada microserviço.

🧪 Testes
Para rodar os testes da aplicação:

Entre no contêiner da aplicação:

Bash

docker-compose exec nome-do-seu-servico bash
Execute os testes:

Bash

npm test # ou o comando de teste do seu projeto (ex: php artisan test, pytest)
📚 Documentação da API
A documentação é a parte mais importante de uma API. Descreva como usar seus endpoints.

Endpoints Disponíveis
GET /api/v1/recurso
Descrição: Retorna uma lista de todos os recursos.

Parâmetros de Query:

limit (opcional): Número de resultados a retornar. Ex: ?limit=10

Resposta:

Sucesso (200 OK):

JSON

[
  {
    "id": 1,
    "nome": "Exemplo"
  }
]
POST /api/v1/recurso
Descrição: Cria um novo recurso.

Corpo da Requisição (JSON):

JSON

{
  "nome": "Novo Exemplo"
}
Resposta:

Sucesso (201 Created):

JSON

{
  "message": "Recurso criado com sucesso!"
}
Erro (400 Bad Request):

JSON

{
  "error": "Nome do recurso é obrigatório."
}
Adicione mais endpoints aqui, seguindo o mesmo formato.
🛠️ Tecnologias Utilizadas
Liste as principais tecnologias, frameworks e bibliotecas usadas no projeto.

Backend: [Nome da Linguagem/Framework] (ex: Node.js, Express)

Banco de Dados: [Nome do Banco de Dados] (ex: PostgreSQL, MongoDB)

Orquestração: Docker, Docker Compose

Testes: [Nome da ferramenta de testes] (ex: Jest, Mocha)

🤝 Contribuindo
Contribuições, issues e sugestões são bem-vindas. Siga estas etapas para contribuir:

Faça um fork do projeto.

Crie uma branch para sua feature (git checkout -b feature/minha-feature).

Commit suas mudanças (git commit -m 'feat: Adiciona nova feature').

Envie para a branch original (git push origin feature/minha-feature).

Abra um Pull Request.

📝 Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE.md para detalhes.

✉️ Contato
Seu Nome/Nome da Equipe - 

E-mail: [seu.email@exemplo.com]

Fique à vontade para entrar em contato com qualquer dúvida ou sugestão.
