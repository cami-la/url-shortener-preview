<h1>url-shortener</h1>
<p>O URL Shortener é um projeto que oferece um serviço de encurtamento de URLs. Destaca-se pela funcionalidade de redirect eficiente, persistência de dados confiável, testes abrangentes (unidade e integração), hospedagem em nuvem pública e documentação.</p>
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
    <a alt="google Guava">
        <img src="https://img.shields.io/badge/guava-v30.1.1-red.svg" />
    </a>
    <a alt="H2">
        <img src="https://img.shields.io/badge/H2-v2.1.214-darkblue.svg" />
    </a>
    <a alt="PostgreSQL">
        <img src="https://img.shields.io/badge/PostgreSQL-v42.5.6-blue.svg" />
    </a>
    <a alt="JUnit5">
        <img src="https://img.shields.io/badge/JUnit5-v5.9.2-darkred.svg" />
    </a>
    <a alt="Mockito">
        <img src="https://img.shields.io/badge/Mockito-v4.8.1-darkgreen.svg" />
    </a>
</p>

## Configuração

Essas instruções fornecerão aos usuários as etapas necessárias para clonar o repositório e iniciar a aplicação em
diferentes ambientes (Unix e Windows) com o perfil de desenvolvimento ativado.

1. Clone o repositório: git clone https://github.com/cami-la/url-shortener.git
2. Inicie a aplicação no ambiente Unix: `./gradlew bootrun --args='--spring.profiles.active=dev'`
3. Inicie a aplicação no ambiente Windows: `gradle.bat bootrun --args='--spring.profiles.active=dev'`

## Uso da API

> Request da requisição

### Criar uma URL curta

POST /

- Descrição: Cria uma URL curta a partir de uma URL original.
- Parâmetros da solicitação:
    - `originalUrl` (obrigatório): A URL original a ser encurtada.
- Exemplo de solicitação:

POST /?originalUrl=https://www.example.com

### Redirecionar para a URL original

GET /{shortUrl}

- Descrição: Redireciona para a URL original com base no código de URL encurtada.
- Exemplo de solicitação:

GET /abc123

> Response da requisição

### Exemplos de Respostas

- Resposta bem-sucedida para criação de URL curta (POST):

HTTP/1.1 201 Created
Content-Type: application/json

{
"id": "12345",
"originalUrl": "https://www.example.com",
"shortUrl": "abc123"
}

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

## Documentação do Swagger

A documentação da API pode ser encontrada no Swagger. Para visualizá-la,
acesse: [Documentação do Swagger](http://localhost:8080/swagger-ui/index.html#/).

## Hospedagem no Railway.app

Este projeto está hospedado no Railway.app. Para acessar a aplicação,
acesse: [URL da Aplicação](https://sua-url-de-hospedagem-aqui).
<h6>Nota: Este projeto não está mais hospedado no https://railway.app/ devido a questões financeiras. No
entanto, a aplicação e o banco de dados estão prontos para serem hospedados no Railway.app. Se você estiver interessado
em vê-los hospedados, por favor, me avise e farei a implantação rapidamente para você. (:</h6>

## Possíveis Melhorias

- Utilizar Migrations com Flyway para gerenciar as alterações no banco de dados de forma controlada e versionada.
- Trocar o Banco de Dados PostgreSQL pelo MongoDB, aproveitando as características e benefícios oferecidos pelo MongoDB.
- Criar um Dockerfile e docker-compose para facilitar o processo de implantação e execução do aplicativo em um ambiente
  de contêiner.
- Configurar CI/CD no GitHub Actions ou no próprio Railway.app para automatizar o processo de construção, testes e
  implantação do aplicativo.

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhoria, fique à vontade para
abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o
arquivo <a href="https://github.com/cami-la/url-shortener-preview/blob/3eb25645b0ca1a1ee8bd8b5de947c11f5a6d42ba/LICENSE.md">(LICENSE)</a> para obter.

<hr>

<h3>Autor</h3>

<a href="https://www.linkedin.com/in/cami-la/">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/64323124?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Camila Cavalcante</b></sub></a> <a href="https://www.instagram.com/camimi_la/" title="Instagram"></a>

Feito com ❤️ por Cami-la 👋🏽 Entre em contato!

[![Linkedin Badge](https://img.shields.io/badge/-Camila-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/cami-la/)](https://www.linkedin.com/in/cami-la/)
[![Gmail Badge](https://img.shields.io/badge/-camiladsantoscavalcante@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:camiladsantoscavalcante@gmail.com)](mailto:camiladsantoscavalcante@gmail.com)



