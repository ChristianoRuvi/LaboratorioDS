# Nome do projeto
Escreva um ou dois parágrafo resumindo o objetivo do seu projeto.

## Integrantes
* Breno de Oliveira Ribeiro
* Christiano Ruvieri Lebre Lima Oliveira
* Nome completo do aluno 3
* Nome completo do aluno 4

## Orientadores
* Cristiano de Macêdo Neto
* Nome completo do professor 2

## Instruções de utilização
Assim que a primeira versão do sistema estiver disponível, deverá complementar com as instruções de utilização. Descreva como instalar eventuais dependências e como executar a aplicação.

## Histórias de Usuário para o Sistema de Matrículas 

## 1. Login de Usuário

**Como** usuário (aluno, professor ou secretário), **quero** fazer login no sistema **para** acessar minhas funcionalidades específicas.

- **Critérios de Aceitação:**
  - O sistema deve solicitar login e senha.
  - O sistema deve validar as credenciais fornecidas.
  - O sistema deve exibir uma mensagem de erro caso as credenciais sejam inválidas.

## 2. Gerenciar Perfil

**Como** aluno, **quero** gerenciar meu perfil **para** atualizar minhas informações pessoais.

- **Critérios de Aceitação:**
  - O aluno deve poder atualizar suas informações pessoais (e.g., email, telefone).
  - O aluno deve poder alterar sua senha.
  - O sistema deve confirmar as alterações com uma mensagem de sucesso.

## 3. Consultar Disciplinas Disponíveis

**Como** aluno, **quero** consultar as disciplinas disponíveis **para** decidir em quais me matricular.

- **Critérios de Aceitação:**
  - O aluno deve poder visualizar uma lista de disciplinas disponíveis.
  - A lista deve incluir informações como nome da disciplina, professor, número de créditos e horários.
  - O sistema deve mostrar a quantidade de vagas disponíveis por disciplina.

## 4. Efetuar Matrícula em Disciplinas

**Como** aluno, **quero** me matricular em disciplinas obrigatórias e optativas durante o período de matrícula **para** garantir minha participação nas aulas.

- **Critérios de Aceitação:**
  - O aluno deve poder selecionar até 4 disciplinas obrigatórias e 2 disciplinas optativas.
  - O sistema deve impedir matrícula em disciplinas que já tenham atingido o número máximo de alunos (60).
  - O sistema deve notificar o aluno quando a matrícula for bem-sucedida.
  - O sistema deve permitir a consulta de disciplinas disponíveis como parte do processo de matrícula.

## 5. Cancelar Matrícula em Disciplinas

**Como** aluno, **quero** cancelar minha matrícula em disciplinas durante o período de matrícula **para** ajustar meu horário ou remover disciplinas que não pretendo cursar.

- **Critérios de Aceitação:**
  - O aluno deve poder visualizar as disciplinas nas quais está matriculado.
  - O aluno deve poder cancelar a matrícula em qualquer disciplina.
  - O sistema deve atualizar a lista de alunos inscritos na disciplina após o cancelamento.
  - O sistema deve notificar o aluno quando o cancelamento for bem-sucedido.
  - O sistema deve permitir a consulta de disciplinas disponíveis como parte do processo de cancelamento.

## 6. Consultar Alunos Matriculados

**Como** professor, **quero** consultar a lista de alunos matriculados em minhas disciplinas **para** acompanhar quem está participando das aulas.

- **Critérios de Aceitação:**
  - O professor deve poder selecionar uma disciplina e visualizar a lista de alunos matriculados.
  - A lista deve incluir o nome dos alunos e outros detalhes relevantes (e.g., número de matrícula).
  - O sistema deve permitir a exportação da lista de alunos para uso offline.

## 7. Gerenciar Disciplinas

**Como** secretário(a), **quero** gerenciar as disciplinas **para** manter o currículo atualizado e disponível para os alunos.

- **Critérios de Aceitação:**
  - O sistema deve permitir a criação de novas disciplinas com nome, código, número de créditos e professor responsável.
  - O sistema deve permitir a edição e remoção de disciplinas existentes.
  - O sistema deve impedir a exclusão de disciplinas que já tenham alunos matriculados, exceto se forem canceladas.
  - O sistema deve incluir a funcionalidade de gerar o currículo semestral.
  - O sistema deve permitir a consulta de alunos matriculados em disciplinas como parte da gestão.

## 8. Gerenciar Professores

**Como** secretário(a), **quero** gerenciar os dados dos professores **para** associá-los corretamente às disciplinas.

- **Critérios de Aceitação:**
  - O sistema deve permitir a criação, atualização e remoção de registros de professores.
  - O sistema deve associar professores às disciplinas que eles ministrarão.
  - O sistema deve garantir que as disciplinas sejam atribuídas a professores antes do início do período de matrícula.

## 9. Gerenciar Alunos

**Como** secretário(a), **quero** gerenciar as informações dos alunos **para** manter os registros atualizados.

- **Critérios de Aceitação:**
  - O sistema deve permitir a criação, atualização e remoção de registros de alunos.
  - O sistema deve associar os alunos aos cursos e às disciplinas em que estão matriculados.
  - O sistema deve garantir a integridade dos dados durante as operações de atualização e remoção.

## 10. Gerar Currículo Semestral

**Como** secretário(a), **quero** gerar o currículo semestral **para** disponibilizar as disciplinas aos alunos.

- **Critérios de Aceitação:**
  - O sistema deve permitir a configuração do currículo semestral, incluindo as disciplinas oferecidas e seus respectivos professores.
  - O sistema deve gerar automaticamente o currículo para o semestre com base nas disciplinas disponíveis.
  - O sistema deve permitir a revisão e a edição do currículo antes de publicá-lo para os alunos.

## 11. Notificar Sistema de Cobranças

**Como** secretário(a), **quero** notificar o sistema de cobranças após o término do período de matrículas **para** que os alunos sejam cobrados pelas disciplinas em que se matricularam.

- **Critérios de Aceitação:**
  - O sistema deve enviar uma notificação ao sistema de cobranças contendo os dados de matrícula de cada aluno após o término do período de matrículas.
  - A notificação deve incluir o nome do aluno, as disciplinas em que está matriculado e o valor total a ser cobrado.
  - O sistema deve confirmar o envio da notificação ao sistema de cobranças.
  - A notificação deve ser realizada como uma extensão do processo de matrícula e cancelamento.


