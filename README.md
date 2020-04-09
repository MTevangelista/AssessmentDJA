# AssessmentDJA
Projeto no Android Studio:

Conceitos utilizados:
- Implementação de login
- Implementação de Cadastro
- Login via Facebook
- Padrão MVVM - Model View ViewModel
- Conexão com o WebService pelo RetroFit.

Tela Inicial: Tela inicial com uma logo, e três botões:
- Login
- Cadastro
- Entrar via Facebook

Tela de Cadastro: Formulário de cadastro com os seguintes campos:
- Nome
- Login (Email)
- Senha
- Confirmar Senha
- CPF

Após o cadastro, o usuário será redirecionado para a Tela de Login para efetuar o Login. 

Tela de Login: Formulário de Login com os seguintes campos:
- Login (Email)
- Senha

Tela Principal: Apresentará uma listagem de tarefas para o usuário executar. Para buscar essas tarefas, será consumido o seguinte endpoint: http://infnet.educacao.ws/dadosAtividades.php

Consumindo apenas os dados do campo “descricao” 
Os dados serão exibidos em formato de ListView. 
Para realizar a conexão com o WebService, será utilizado o RetroFit.
