SISTEMA DE AGENDAMENTO DE EVENTOS

Sobre o Projeto:

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
git clone https://github.com/bgrbarbosadev/comunicacao-sincrona-microservices.git </br>
cd comunicacao-sincrona-microservices </br>

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

Para testes, após a inicialização dos containers só executar o conjunto de requisições disponibilizadas através do arquivo postman contendo as colections utilizadas no projeto.
Nome do arquivo: Microservice-cominicação sincrona.postman_collection.json

🛠️ Tecnologias Utilizadas
Liste as principais tecnologias, frameworks e bibliotecas usadas no projeto.

Backend: Java versão: 11

Banco de Dados: Postgres 

Orquestração: Docker, Docker Compose

📝 Licença
Este projeto é livre. Desenvolvido para práticas acadêmicas.

✉️ Contato
Bruno Gaspar Romeiro Barbosa - (24)98854-9631

E-mail: [bgrbarbosa@hotmail.com]

Fique à vontade para entrar em contato com qualquer dúvida ou sugestão.
