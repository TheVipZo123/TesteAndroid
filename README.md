# Teste Tecnico Android - Lista de Tarefas
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


# O que há nesse Projeto

## 🚀 Funcionalidades

- ✅ **Adicionar tarefas** — o usuário pode criar novas tarefas por meio de um campo de texto.  
- 🔄 **Marcar como concluída** — cada tarefa possui um *CheckBox* que pode ser marcado/desmarcado.  
- ❌ **Excluir tarefas** — é possível remover tarefas individualmente através de um botão com ícone de “X”.  
- 🧠 **Gerenciamento de estado com ViewModel** — o app utiliza `LoginViewModel` e `TaskViewModel` para separar a lógica da interface.  
- 🖥️ **Interface responsiva** — layout feito com `RecyclerView` para exibir a lista de tarefas dinamicamente.  
- 💾 **LiveData** — as alterações na lista são refletidas automaticamente na interface.
