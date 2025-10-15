# Teste Tecnico Android
<div align="center">
  <img src="https://github.com/user-attachments/assets/2c303b03-2c49-4d6f-a148-9fadc9f748d4" alt="Demo" width="300"/>
</div>

# Instruções do Projeto

### RF (Requisitos Funcionais)

- Tela de LOGIN com autenticação, usando USUÁRIO e SENHA.
- Tela de Listagem de tarefas
- Botão de CHECK em cada tarefa
- Botão para fazer LOGOUT
- Botão para REMOVER uma tarefa da lista
- Botão para ADICIONAR uma tarefa na lista

### RNF (Requisitos Não Funcionais)

**(Obrigatório)**

- O aplicativo deve ser feito em Kotlin
- Um repositório deve ser criado no GITHUB, desde o início do desenvolvimento.
- Cada nova implementação e/ou correção, um novo commit deve ser gerado, mantendo uma linha do tempo no desenvolvimento.
- As telas do projeto devem seguir o padrão:  XML + Fragment (View-based UI)

**(Não obrigatório, mas pode te dar pontos)**

- Banco de dados para persistência dos dados.
- Método de criptografia de senha.
- Uso de CONVENTIONAL COMMITS
- Telas adicionais que complementem o app.

### Regras de negócio

- Autenticação deve ser validada, e em caso de falha, um retorno deve ser lançado ao usuário. ("Autenticação Falhou", "Usuário e/ou Senha incorretos")
- Após logar com sucesso, o usuário deve ver uma LISTA de TAREFAS.
- Na LISTA DE TAREFAS, o usuário deve poder dar um CHECK na tarefa, informando que ela já foi concluída
- Na LISTA DE TAREFAS, o usuário deve poder CRIAR uma nova tarefa, informando a descrição.
- Na LISTA DE TAREFAS, o usuário deve poder DELETAR uma tarefa, com um aviso para confirmação da ação.
- O usuário deve poder realizar o LOGOUT do aplicativo.
