<h1>url-shortener</h1>
<p align="center">Esta é uma API para encurtar URLs e redirecionar para a URL original.</p>
<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v17-blue.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v3.0.6-brightgreen.svg" />
    </a>
    <a alt="Gradle">
        <img src="https://img.shields.io/badge/Gradle-v7.6-lightgreen.svg" />
    </a>
    <a alt="H2">
        <img src="https://img.shields.io/badge/guava-v30.1.1-darkred.svg" />
    </a>
    <a alt="H2">
        <img src="https://img.shields.io/badge/H2-v2.1.214-darkblue.svg" />
    </a>
    <a alt="PostgreSQL">
        <img src="https://img.shields.io/badge/PostgreSQL-v42.5.6-blue.svg" />
    </a>
</p>

<h3 align="center">▶️ <a href="">Playlist no YouTube</a></h3>

## Configuração

1. Clone o repositório: git clone https://github.com/seu-usuario/url-shortener.git
2. Inicie a aplicação:
./gradlew bootrun

## Uso da API

> Request da requisição

### Redirecionar para a URL original

GET /urls/{shortUrl}

- Descrição: Redireciona para a URL original com base no código de URL encurtada.
- Exemplo de solicitação:

GET /urls/abc123

### Criar uma URL curta

POST /urls

- Descrição: Cria uma URL curta a partir de uma URL original.
- Corpo da solicitação: JSON contendo a URL original.
- Exemplo de solicitação:

POST /urls
Content-Type: application/json

{
"originalUrl": "https://www.example.com"
}


> Response da requisição

### Exemplos de Respostas

- Resposta bem-sucedida para redirecionamento (GET):

HTTP/1.1 301 Moved Permanently
Location: https://www.example.com

- Resposta mal-sucedida para redirecionamento (GET):

HTTP/1.1 404 Not Found
Content-Type: application/json

{
  "message": "URL Not Found",
  "timestamp": "2023-05-18T10:30:00",
  "status": 404,
  "error": "class dev.camila.url.shortener.exception.BusinessException",
  "details": {
    "Cause": "'abc123' not found"
  }
}

- Resposta bem-sucedida para criação de URL curta (POST):

HTTP/1.1 201 Created
Content-Type: application/json

{
"id": "12345",
"originalUrl": "https://www.example.com",
"shortUrl": "abc123"
}

## Documentação do Swagger

A documentação da API pode ser encontrada no Swagger. Para visualizá-la, acesse: [Documentação do Swagger](http://localhost:8080/swagger-ui/index.html#/).

## Hospedagem no Railway.app

Este projeto está hospedado no Railway.app. Para acessar a aplicação, acesse: [URL da Aplicação](https://sua-url-de-hospedagem-aqui).

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhoria, fique à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [Licença MIT](LICENSE) para obter.


<hr>

<h3>Autor</h3>

<a href="https://www.linkedin.com/in/cami-la/">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/64323124?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Camila Cavalcante</b></sub></a> <a href="https://www.instagram.com/camimi_la/" title="Instagram"></a>

Feito com ❤️ por Cami-la 👋🏽 Entre em contato!

[![Linkedin Badge](https://img.shields.io/badge/-Camila-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/cami-la/)](https://www.linkedin.com/in/cami-la/)
[![Gmail Badge](https://img.shields.io/badge/-camiladsantoscavalcante@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:camiladsantoscavalcante@gmail.com)](mailto:camiladsantoscavalcante@gmail.com)



