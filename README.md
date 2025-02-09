# Estudo de SQS e LocalStack

Esta aplicação foi desenvolvida com o propósito de fornecer um ambiente de estudo e experimentação para o uso do Amazon SQS (Simple Queue Service) e do LocalStack, uma ferramenta que emula serviços da AWS localmente. O objetivo principal é facilitar a compreensão e a implementação de filas SQS em um ambiente controlado e sem custos, permitindo que desenvolvedores e equipes de desenvolvimento testem e experimentem com a integração de filas de mensagens em suas aplicações sem a necessidade de interagir com a AWS real.

## Objetivo de Estudo

O principal objetivo desta aplicação é proporcionar uma plataforma para estudar e experimentar com SQS e LocalStack, permitindo que os usuários:

- **Compreendam o funcionamento das filas de mensagens** em um ambiente isolado.
- **Testem diferentes cenários e configurações** sem custos associados.
- **Desenvolvam e validem integrações com o SQS** de forma prática e direta.

## Funcionalidades

- **Integração com Amazon SQS**: Demonstra como enviar e receber mensagens usando o serviço SQS da AWS, proporcionando uma visão clara do fluxo de mensagens.
- **Utilização do LocalStack**: Emula os serviços da AWS localmente, permitindo testes e desenvolvimento sem custos.
- **Configuração Simples**: A configuração da aplicação e do LocalStack é gerenciada através de um arquivo Docker Compose.
- **Testes e Demonstrações**: Inclui exemplos práticos e testes para mostrar como as mensagens são enviadas, processadas e recebidas.
