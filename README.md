## Projeto Login Screen

Este projeto consiste em uma aplicação web para autenticação de usuários e gerenciamento de contas. Ele inclui funcionalidades para criar novas contas de usuário, fazer login, atualizar a senha e exibir uma página de boas-vindas após o login bem-sucedido.

## Estrutura do Projeto
```
main
├── java
│   └── com
│       └── loginscreen
│           ├── dao
│           │   ├── UsuarioDaoImpl.java
│           │   └── Dao.java
│           ├── main
│           │   └── ConnectionFactory.java
│           ├── models
│           │   └── Usuario.java
│           ├── resources
│           │   └── dbconnection.properties
│           └── servlets
│               ├── AtualizarSenhaServlet.java
│               ├── CriarUsuarioServlet.java
│               └── ValidarLoginServlet.java
└── webapp
    ├── META-INF
    │   └── MANIFEST.MF
    └── WEB-INF
        ├── jsps
        │   ├── index.jsp
        │   ├── criarUsuario.jsp
        |   ├── atualizarSenha.jsp
        │   └── boasVindas.jsp
        ├── lib
        │   ├── jakarta.servlet.jsp.jstl-3.0.0.jar
        │   ├── jakarta.servlet.jsp.jstl-api-3.0.0.jar
        │   └── mysql-connector-j-8.3.0.jar
        └── web.xml
```

### Arquivos .java

- **UsuarioDaoImpl.java**: Implementa a interface `Dao` para operações relacionadas à entidade `Usuario` no banco de dados. Inclui métodos para inserir, buscar, listar, atualizar e excluir usuários.
- **ConnectionFactory.java**: Fornece métodos para obter uma conexão com o banco de dados MySQL, utilizando as configurações definidas no arquivo `dbconnection.properties`.
- **Usuario.java**: Define a estrutura da classe `Usuario`, que representa um usuário na aplicação.

### Arquivos .jsp

- **index.jsp**: Página de login onde os usuários podem inserir seu email e senha para acessar a aplicação. Também inclui links para criar uma nova conta e para redefinir a senha.
- **criarUsuario.jsp**: Formulário para criar uma nova conta de usuário, solicitando email e senha.
- **atualizarSenha.jsp**: Formulário para atualizar a senha de um usuário existente, solicitando o email, a nova senha e a confirmação da nova senha.
- **boasVindas.jsp**: Página exibida após um login bem-sucedido, dando as boas-vindas ao usuário logado.

### Servlets

- **ValidarLogin.java**: Servlet responsável por validar o login do usuário. Ele recebe as credenciais (email e senha) do formulário de login e verifica se correspondem a um usuário existente no banco de dados.
- **CriarUsuarioServlet.java**: Servlet responsável por criar um novo usuário. Ele recebe os dados do formulário de criação de conta e adiciona um novo usuário ao banco de dados.
- **AtualizarSenhaServlet.java**: Servlet responsável por atualizar a senha do usuário. Ele recebe o email e a nova senha do formulário de atualização de senha e atualiza a senha correspondente no banco de dados.

### Arquivo web.xml

- **web.xml**: Arquivo de configuração do Servlet, que mapeia os URLs dos servlets definidos no projeto.
