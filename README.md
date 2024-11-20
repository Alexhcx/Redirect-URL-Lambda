# Redirect URL Lambda - Projeto Rocketseat

## Descrição

Este projeto Lambda redireciona URLs curtas para suas URLs originais. Ele foi desenvolvido como parte do curso gratuito de Java da Rocketseat, que aborda o desenvolvimento de uma aplicação serverless de encurtamento de URLs usando AWS. [1]

## Funcionalidade

O Lambda recebe um código de URL curta como entrada e busca a URL original correspondente em um bucket S3. [2] Se a URL for válida (não expirada), o Lambda retorna um código de status 302 (redirecionamento) com a URL original no cabeçalho "Location". Se a URL estiver expirada, o Lambda retorna um código de status 410 (Gone) com a mensagem "This Url has expired". [2]

## Detalhes Técnicos

* Utiliza AWS Lambda, S3 e API Gateway. [1, 2]
* A URL original e a data de expiração são armazenadas em um arquivo JSON no S3. [2]
* O código-fonte está disponível no GitHub. [3]

## Instruções de Uso

**Pré-requisitos:** Uma conta AWS, Java JDK instalado.

**Deploy:** 

1.  Faça o download do código-fonte do projeto no GitHub. [3]
2.  Utilize o Serverless Framework para fazer o deploy do Lambda na AWS. 

**Testes:**

1.  **Localmente:** Utilize um ambiente de desenvolvimento Java para executar o código e simular requisições.
2.  **Na AWS:** Utilize o console da AWS Lambda para invocar o Lambda com diferentes códigos de URL.

## Informações Adicionais

Este projeto faz parte de um sistema de encurtamento de URLs que também inclui um Lambda para criar novas URLs curtas. [1] 

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto é licenciado sob a licença MIT.
