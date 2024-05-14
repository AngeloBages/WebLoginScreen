# Login Screen

Este projeto consiste em uma aplicação web de tela de login, onde os usuários podem criar contas, fazer login, atualizar suas senhas e visualizar uma página de boas-vindas após o login bem-sucedido. Abaixo estão descritas as principais classes e funcionalidades do projeto:


```
target
├── classes
│   ├── com
│   │   └── loginscreen
│   │       ├── dao
│   │       │   ├── implementations
│   │       │   │   ├── UsuarioDaoImpl.class
│   │       │   │   └── UsuarioDaoJpaImpl.class
│   │       │   └── interfaces
│   │       │       ├── Dao.class
│   │       │       └── UsuarioDao.class
│   │       ├── models
│   │       │   └── Usuario.class
│   │       ├── resources
│   │       │   └── dbconnection.properties
│   │       ├── servlets
│   │       │   ├── AtualizarSenhaServlet.class
│   │       │   ├── CriarUsuarioServlet.class
│   │       │   ├── DisplayHomeServlet.class
│   │       │   ├── DisplayMenuServlet.class
│   │       │   ├── ServletController.class
│   │       │   └── ValidarLoginServlet.class
│   │       └── utils
│   │           ├── ConnectionFactory.class
│   │           ├── JpaUtils.class
│   │           └── ServletAction.class
│   └── META-INF
│       └── persistence.xml
└── m2e-wtp
    └── web-resources
        ├── META-INF
        │   ├── maven
        │   │   └── com.login-screen
        │   │       └── login-screen
        │   │           ├── pom.properties
        │   │           └── pom.xml
        │   └── MANIFEST.MF
        └── WEB-INF
            ├── jsps
            │   ├── atualizarSenha.jsp
            │   ├── boasVindas.jsp
            │   ├── criarUsuario.jsp
            │   └── index.jsp
            └── web.xml

```

### DAO (Data Access Object)

#### Pacote `com.loginscreen.dao.implementations`

- **UsuarioDaoImpl.java**: Implementa a interface `UsuarioDao` para realizar operações CRUD na entidade `Usuario` usando JDBC (Java Database Connectivity).

- **UsuarioDaoJpaImpl.java**: Implementa a interface `UsuarioDao` para realizar operações CRUD na entidade `Usuario` usando JPA (Java Persistence API).

#### Pacote `com.loginscreen.dao.interfaces`

- **Dao.java**: Interface genérica para operações de acesso a dados, como inserção, consulta, atualização e exclusão.

- **UsuarioDao.java**: Interface que define métodos para operações CRUD na entidade `Usuario`.

### Models

#### Pacote `com.loginscreen.models`

- **Usuario.java**: Representa um usuário com atributos como email e senha.

### Servlets

#### Pacote `com.loginscreen.servlets`

- **AtualizarSenhaServlet.java**: Servlet que permite ao usuário atualizar sua senha.
- **CriarUsuarioServlet.java**: Servlet que permite ao usuário criar uma nova conta.
- **DisplayHomeServlet.java**: Servlet que exibe uma página de boas-vindas após o login bem-sucedido.
- **DisplayMenuServlet.java**: Servlet que exibe o menu principal da aplicação.
- **ServletController.java**: Servlet controladora que despacha solicitações para outras servlets.

### Utils

#### Pacote `com.loginscreen.utils`

- **ConnectionFactory.java**: Responsável por fornecer uma conexão com o banco de dados MySQL usando JDBC.
- **JpaUtils.java**: Responsável por fornecer o objeto `EntityManager` do JPA.
- **ServletAction.java**: Define uma interface que possui métodos para execução de ações nos servlets da aplicação.

### Arquivos de Configuração

- **persistence.xml**: Arquivo de configuração do JPA que define as propriedades de persistência.
- **web.xml**: Arquivo de configuração do web-app, define configurações como welcome-file e mapeamento de servlets.
- **pom.xml**: Arquivo de configuração do Maven que define as dependências e configurações do projeto.
- **dbconnection.properties**: Arquivo de propriedades que contém informações de configuração para a conexão com o banco de dados.

## Configuração do Banco de Dados

Certifique-se de configurar corretamente o banco de dados MySQL com as seguintes informações:

- URL: jdbc:mysql://localhost:3306/fj21?useSSL=false&allowPublicKeyRetrieval=true
- Usuário: root
- Senha: root

## Dependências

O projeto foi construído usando Maven com as seguintes dependências:

- Hibernate Core: Version 6.5.0.Final
- Jakarta Servlet JSP JSTL API: Version 3.0.0
- Jakarta Servlet JSP JSTL: Version 3.0.0
- MySQL Connector/J: Version 8.0.33

## Tecnologias Utilizadas

- Java
- JSP (JavaServer Pages)
- JSTL (Java Standard Tag Library)
- Servlets
- MySQL
- JPA (Java Persistence API)
- Hibernate
