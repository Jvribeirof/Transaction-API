# API de Transações Financeiras

Um projeto de back-end robusto para demonstrar um ciclo de
desenvolvimento profissional, desde a concepção e arquitetura até o
deploy e monitoramento.

## 📖 Sobre o Projeto

Esta API foi desenvolvida como uma demonstração das melhores
práticas de engenharia de software no desenvolvimento back-end. Ela
simula um sistema de transações financeiras, focando não apenas na
lógica de negócio, mas também na qualidade, manutenibilidade, e na
experiência do desenvolvedor.

O sistema é projetado para lidar com desafios do mundo real, como o
tratamento correto de moedas (currency) e a complexidade de
fusos horários, garantindo a integridade dos dados em todas as
operações.

## ✨ Principais Features

-   **Gestão de Transações**: Funcionalidades completas para envio
    e consulta de transações.
-   **Tratamento de Dados Financeiros**: Lógica implementada para lidar
    com diferentes moedas e garantir a precisão dos valores.
-   **Padrão ISO 8601**: Consistência e padronização no tratamento de
    datas e horas, com suporte a fusos horários.
-   **Documentação Interativa**: API totalmente documentada com Swagger
    (OpenAPI) para facilitar o uso e a integração.
-   **Ambiente Containerizado**: Uso de Docker e Docker Compose para
    garantir um ambiente de desenvolvimento e produção consistente e de
    fácil configuração.
-   **Observabilidade**: Sistema de logs estruturados para monitoramento
    e depuração facilitada.
-   **Tratamento de Erros**: Respostas de erro padronizadas e claras
    para uma melhor experiência do cliente da API.
-   **Suíte de Testes Completa**: Alta cobertura de testes com testes
    unitários e de integração.

## ✨ Nova Features
-   **Autenticação (JWT)**: Sistema de autenticação de 2 usuários para
    limitar o acesso à API.
-   **Autorização**: Sistema de autorização que permite a a restrição
    de usuários a certos endpoints. O tipo de autenticação uzado foi JWT
    de forma que mantenha o alto nível de segurança do sistema.
