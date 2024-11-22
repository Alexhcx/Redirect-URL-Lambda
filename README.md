# Redirect URL

## Visão Geral

Este projeto é uma função Lambda escrita em Java que redireciona URLs curtas. Ele utiliza os seguintes serviços da AWS:

* **AWS S3:** Armazena os dados das URLs, incluindo a URL original e a data de expiração, em arquivos JSON.
* **API Gateway:** Expõe um endpoint para a função Lambda, permitindo que ela seja acessada publicamente.

## Descrição

A função Lambda recebe uma URL curta como entrada. O código extrai o código da URL curta do parâmetro `rawPath` da requisição. Em seguida, busca no bucket do S3 `projeto-lambda-shortener-url-ale` um arquivo JSON com o nome `shortUrlCode.json`. Esse arquivo contém a URL original e a data de expiração. Se a URL for válida, o usuário é redirecionado para a URL original. Caso contrário, uma mensagem de erro "This Url has expired" é exibida.

## Explicação do Código

* **Classe App:** Implementa a interface `RequestHandler` do AWS Lambda.
    * **Método handleRequest:** Processa as requisições recebidas pela função Lambda.
        * Obtém o código da URL curta a partir do parâmetro `rawPath`.
        * Constrói um objeto `GetObjectRequest` para buscar o arquivo JSON correspondente no S3.
        * Utiliza o cliente S3 (`s3Client`) para buscar o arquivo JSON.
        * Converte o conteúdo do arquivo JSON em um objeto `UrlData` usando a biblioteca Jackson.
        * Verifica se a URL está expirada comparando o tempo atual com a data de expiração.
        * Retorna uma resposta HTTP com o status code 302 (redirecionamento) e a URL original no header `Location` se a URL for válida.
        * Retorna uma resposta HTTP com o status code 410 (Gone) e a mensagem "This Url has expired" se a URL estiver expirada.

* **Classe UrlData:** Representa os dados de uma URL, incluindo a URL original e a data de expiração.

## Dependências

* **AWS SDK para Java:** Permite a interação com os serviços da AWS, como S3 e Lambda.
* **Jackson:** Biblioteca para serialização e desserialização de objetos JSON.
* **Maven:** Ferramenta de gerenciamento de dependências e build do projeto.

## Exemplo de Uso
https://{seu_endpoint_api_gateway}/{url_curta}

**Observações:**

* O endpoint do API Gateway e o código da URL curta devem ser substituídos pelos valores reais do seu projeto.

## Informações Adicionais

* Este projeto foi desenvolvido como parte do Curso Gratuito de Java da Rocketseat. O certificado de participação está disponível [aqui](source 1). 
* O curso abrangeu tópicos como desenvolvimento de aplicações serverless com Java e Maven, integração com AWS S3, API Gateway e Lambda, e manipulação de dados em JSON.
* O código fonte do projeto está disponível no GitHub em:
    * **Redirect URL:** [https://github.com/Alexhcx/Redirect-URL-Lambda](https://github.com/Alexhcx/Redirect-URL-Lambda)
    * **Create URL:** [https://github.com/Alexhcx/create-URL-Lambda](https://github.com/Alexhcx/create-URL-Lambda)
