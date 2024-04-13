![Last Commit](https://img.shields.io/github/last-commit/MateusLeviDev/lev-microservices)


![Screenshot from 2024-04-10 19-15-31](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/4f3ea629-3a22-43d3-b429-aa6d4c2f59d1)

![Screenshot from 2024-04-10 19-19-33](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/facfd513-61c0-47e2-902b-46529f63e709)

KEYCLOAK <br>
![image](https://github.com/MateusLeviDev/springboot-microservices/assets/101754313/3a409f01-96ff-40f7-8b08-988d57139622)



<details>
  <summary>Use Case</summary>
  

  `Testcontainers`: is a testing library that provides easy and lightweight APIs for bootstrapping integration tests with real services wrapped in Docker containers. <br>
  permitem a execução de testes em ambientes isolados <br>
  testar a aplicação com os mesmos serviços que serão utilizados em produção. Configuração Simplificada, Limpeza Automática <br>
  podem ser executados em diversos ambientes, incluindo máquinas locais de desenvolvimento, servidores de integração contínua (CI), e ambientes de nuvem, desde que  <br>
  suportem Docker. <br>

  `KEYCLOAK`: é uma ferramenta criada pela empresa Red Hat e que faz o gerenciamento de credenciais de usuários e de suas permissões.  <br>
  O Keycloak pode também ser o repositório oficial de usuários de uma companhia, seja através de um cadastro de todos ou usuários, ou de um vínculo com uma base de dados  <br>
  Além desses conceitos mais básicos, tem mais 2 que vale a pena comentarmos aqui:<br>

  Identity Providers: São provedores de identidade externos a companhia para que o usuário possa fazer seu próprio cadastro, como por exemplo, login através do Google, 
  Facebook, Linkedin, etc. User Federation: Base de dados externas onde podemos vincular com o Keycloak. <br>
  Exemplo, se a companhia já possui uma base ldap com todos os usuários, é possível conectar com o Keycloak e automaticamente obter todos esses usuários sem que seja 
  necessário o cadastro individual (e manual) deles. já existente, como o ldap, por exemplo. <br>

  `EUREKA`: atuar como um servidor de registro centralizado, onde cada microserviço registrado informa ao servidor sua localização (endereço IP e porta)
  A arquitetura baseada em Eureka segue um modelo cliente-servidor, onde cada serviço cliente registra-se no servidor Eureka quando é iniciado e cancela o <br>
  registro quando é encerrado. O servidor Eureka mantém um registro atualizado de todos os serviços registrados e fornece uma interface de consulta para os 
  clientes procurarem e descobrirem os serviços disponíveis. também oferece recursos de tolerância a falhas e balanceamento de carga. 
  trabalhar e monitorar os microservices.<br>

  `Uso de mongodb e mysql`: a escolha do banco de dados para cada serviço geralmente é baseada em vários fatores, incluindo requisitos de negócios, necessidades de 
  desempenho, escalabilidade e preferências da equipe de desenvolvimento. MongoDB - Modelagem de Dados Flexível, ideal para cenários onde os dados podem ter estruturas variáveis <br>
  ou semi-estruturadas, como os detalhes de um produto que podem variar dependendo do tipo de produto. Escalabilidade Horizontal: MongoDB é altamente escalável e suporta facilmente 
  a distribuição de dados em vários nós<br>
  MySQL - Consistência e Integridade de Dados, Relacionamentos e consultas Complexas

  A comunicação `assíncrona` é adequada quando o serviço de pedidos não precisa esperar uma resposta imediata do serviço de notificação para concluir a operação. Em vez disso, ele apenas envia 
  uma mensagem de notificação para o serviço de notificação e continua com sua operação.
  Benefícios:

    Desacoplamento: A comunicação assíncrona permite um acoplamento mais fraco entre os serviços, pois o serviço de pedidos não precisa esperar pela resposta do serviço de notificação.
    Resiliência: Se o serviço de notificação estiver temporariamente indisponível, o serviço de pedidos pode continuar funcionando normalmente e tentar enviar a mensagem de notificação posteriormente, sem interrupção.
    Escalabilidade: Como o serviço de pedidos não está esperando pela resposta do serviço de notificação, ele pode processar pedidos em paralelo e escalar horizontalmente de forma mais eficiente.

  A comunicação `síncrona` é adequada quando o serviço de pedidos precisa da resposta imediata do serviço de inventário para concluir a operação de pedido. Por exemplo, o serviço de pedidos pode precisar verificar 
  se há inventário suficiente antes de confirmar um pedido.
  
  
</details>
