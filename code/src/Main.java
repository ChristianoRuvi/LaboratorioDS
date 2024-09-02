import Entities.*;
import java.io.*;
import java.util.*;

public class Main {
    private static DadosUniversidade dados;

    public static void main(String[] args) {
        // Try to load data from the file
        dados = carregarDadosDeTxt();

        // If no data is found, create default data
        if (dados == null) {
            dados = criarDadosIniciais();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo ao Sistema de Matrículas da Universidade");
            System.out.println("1. Login como Secretaria");
            System.out.println("2. Login como Professor");
            System.out.println("3. Login como Aluno");
            System.out.println("4. Cadastrar Novo Usuário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    loginSecretaria(scanner);
                    break;
                case 2:
                    loginProfessor(scanner);
                    break;
                case 3:
                    loginAluno(scanner);
                    break;
                case 4:
                    cadastrarUsuario(scanner);
                    salvarDadosEmTxt(dados);
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    salvarDadosEmTxt(dados);
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static DadosUniversidade carregarDadosDeTxt() {
        File file = new File("dados_universidade.txt");
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado. Criando dados iniciais.");
            return null;
        }

        Secretaria secretaria = null;
        Professor professor = null;
        List<Curso> cursos = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();
        Curso cursoAtual = null;


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("Secretaria:")) {
                    secretaria = readSecretaria(reader);
                } else if (line.startsWith("Professor:")) {
                    professor = readProfessor(reader);
                } else if (line.startsWith("Curso:")) {
                    cursoAtual = readCurso(reader);
                    cursos.add(cursoAtual);
                } else if (line.startsWith("Disciplina:")) {
                    if (cursoAtual != null) {
                        Disciplina disciplina = readDisciplina(reader);
                        cursoAtual.adicionarDisciplina(disciplina);
                    }
                } else if (line.startsWith("Aluno:")) {
                    alunos.add(readAluno(reader));
                } else if (line.startsWith("Alunos Matriculados:")) {
                    // No action needed for header, handled within readDisciplina method
                } else {
                    System.err.println("Linha desconhecida: " + line);
                }
            }
            return new DadosUniversidade(secretaria, professor, cursos, alunos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Aluno readAluno(BufferedReader reader) throws IOException {
        String matricula = readValue(reader, "Matrícula");
        String nome = readValue(reader, "Nome");
        String email = readValue(reader, "Email");
        String telefone = readValue(reader, "Telefone");
        String senha = readValue(reader, "Senha");
        return new Aluno(matricula, nome, email, telefone, senha);
    }


    private static Secretaria readSecretaria(BufferedReader reader) throws IOException {
        String id = readValue(reader, "ID");
        String nome = readValue(reader, "Nome");
        String email = readValue(reader, "Email");
        String senha = readValue(reader, "Senha");
        return new Secretaria(id, nome, email, senha);
    }

    private static Professor readProfessor(BufferedReader reader) throws IOException {
        String id = readValue(reader, "ID");
        String nome = readValue(reader, "Nome");
        String email = readValue(reader, "Email");
        String senha = readValue(reader, "Senha");
        return new Professor(id, nome, email, senha);
    }

    private static Curso readCurso(BufferedReader reader) throws IOException {
        String nome = readValue(reader, "Nome do Curso");
        int creditosTotais = Integer.parseInt(readValue(reader, "Créditos Totais"));
        return new Curso(nome, creditosTotais);
    }

    private static Disciplina readDisciplina(BufferedReader reader) throws IOException {
        String nome = readValue(reader, "Nome");
        int cargaHoraria = Integer.parseInt(readValue(reader, "Carga Horária"));
        Disciplina disciplina = new Disciplina(nome, cargaHoraria);

        String line;
        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
            line = line.trim();
            if (line.startsWith("Professor ID:")) {
                String professorId = line.split(": ")[1].trim();
                // Lookup professor by ID in DadosUniversidade
                if (dados != null && dados.getProfessor() != null && dados.getProfessor().getId().equals(professorId)) {
                    disciplina.setProfessor(dados.getProfessor());
                }
            } else if (line.contains(",")) {
                // Parse Alunos Matriculados
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    Aluno aluno = new Aluno(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    disciplina.matricularAluno(aluno);
                }
            }
        }

        return disciplina;
    }

    private static String readValue(BufferedReader reader, String fieldName) throws IOException {
        String line = reader.readLine();
        if (line != null && line.contains(": ")) {
            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                return parts[1].trim();
            }
        }
        System.err.println("Erro ao carregar campo '" + fieldName + "' na linha: " + (line == null ? "null" : line));
        return ""; // Return an empty string if the line is invalid
    }

    public static void salvarDadosEmTxt(DadosUniversidade dados) {
        try (FileWriter writer = new FileWriter("dados_universidade.txt")) {
            // Save Secretaria
            if (dados.getSecretaria() != null) {
                writer.write("Secretaria:\n");
                writer.write("ID: " + dados.getSecretaria().getId() + "\n");
                writer.write("Nome: " + dados.getSecretaria().getNome() + "\n");
                writer.write("Email: " + dados.getSecretaria().getEmail() + "\n");
                writer.write("Senha: " + dados.getSecretaria().getSenha() + "\n\n");
            }

            // Save Professor
            if (dados.getProfessor() != null) {
                writer.write("Professor:\n");
                writer.write("ID: " + dados.getProfessor().getId() + "\n");
                writer.write("Nome: " + dados.getProfessor().getNome() + "\n");
                writer.write("Email: " + dados.getProfessor().getEmail() + "\n");
                writer.write("Senha: " + dados.getProfessor().getSenha() + "\n\n");
            }

            // Save Cursos and Disciplinas
            for (Curso curso : dados.getCursos()) {
                writer.write("Curso:\n");
                writer.write("Nome: " + curso.getNome() + "\n");
                writer.write("Créditos Totais: " + curso.getCreditosTotais() + "\n");

                for (Disciplina disciplina : curso.getDisciplinas()) {
                    writer.write("Disciplina:\n");
                    writer.write("Nome: " + disciplina.getNome() + "\n");
                    writer.write("Carga Horária: " + disciplina.getCargaHoraria() + "\n");
                    if (disciplina.getProfessor() != null) {
                        writer.write("Professor ID: " + disciplina.getProfessor().getId() + "\n");
                    }

                    writer.write("Alunos Matriculados:\n");
                    for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                        writer.write(aluno.getMatricula() + "," + aluno.getNome() + "," + aluno.getEmail() + "," + aluno.getTelefone() + "," + aluno.getSenha() + "\n");
                    }
                    writer.write("\n");
                }

                writer.write("\n"); // Separate each course with a newline
            }

            // Save Alunos (General Information)
            for (Aluno aluno : dados.getAlunos()) {
                writer.write("Aluno:\n");
                writer.write("Matrícula: " + aluno.getMatricula() + "\n");
                writer.write("Nome: " + aluno.getNome() + "\n");
                writer.write("Email: " + aluno.getEmail() + "\n");
                writer.write("Telefone: " + aluno.getTelefone() + "\n");
                writer.write("Senha: " + aluno.getSenha() + "\n\n");
            }

            System.out.println("Dados salvos em dados_universidade.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DadosUniversidade criarDadosIniciais() {
        Secretaria secretaria = new Secretaria("1", "Secretaria Central", "secretaria@universidade.com", "admin123");
        Professor professor = new Professor("1", "Professor Xavier", "xavier@universidade.com", "prof123");
        Curso curso = new Curso("Engenharia de Software", 180);
        List<Curso> cursos = new ArrayList<>();
        cursos.add(curso);
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno("20231001", "João Silva", "joao@universidade.com", "123456789", "aluno123"));
        alunos.add(new Aluno("20231002", "Maria Souza", "maria@universidade.com", "987654321", "aluno123"));

        return new DadosUniversidade(secretaria, professor, cursos, alunos);
    }

    private static void loginSecretaria(Scanner scanner) {
        System.out.print("Digite o ID da Secretaria: ");
        String id = scanner.nextLine();

        if (dados.getSecretaria() != null && dados.getSecretaria().getId().equals(id)) {
            System.out.print("Digite a senha da Secretaria: ");
            String senha = scanner.nextLine();
            if (dados.getSecretaria().getSenha().equals(senha)) {
                menuSecretaria(scanner);
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("ID da secretaria não encontrado.");
        }
    }

    private static void loginProfessor(Scanner scanner) {
        System.out.print("Digite o ID do Professor: ");
        String id = scanner.nextLine();

        if (dados.getProfessor() != null && dados.getProfessor().getId().equals(id)) {
            System.out.print("Digite a senha do Professor: ");
            String senha = scanner.nextLine();
            if (dados.getProfessor().getSenha().equals(senha)) {
                menuProfessor(scanner);
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("ID do professor não encontrado.");
        }
    }

    private static void loginAluno(Scanner scanner) {
        System.out.print("Digite a matrícula do Aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = null;
        for (Aluno a : dados.getAlunos()) {
            if (a.getMatricula().equals(matricula)) {
                aluno = a;
                break;
            }
        }

        if (aluno != null) {
            System.out.print("Digite a senha do Aluno: ");
            String senha = scanner.nextLine();
            if (aluno.getSenha().equals(senha)) {
                menuAluno(scanner, aluno);
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("Matrícula não encontrada.");
        }
    }

    private static void menuSecretaria(Scanner scanner) {
        while (true) {
            System.out.println("Menu da Secretaria:");
            System.out.println("1. Criar Curso");
            System.out.println("2. Gerar Currículo Semestral");
            System.out.println("3. Gerenciar Disciplinas");
            System.out.println("4. Alocar Professor");
            System.out.println("5. Notificar Sistema de Cobranças");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (escolha) {
                case 1:
                    criarCurso(scanner);
                    break;
                case 2:
                    gerarCurriculoSemestral();
                    break;
                case 3:
                    gerenciarDisciplinas(scanner);
                    break;
                case 4:
                    alocarProfessor(scanner);
                    break;
                case 5:
                    notificarSistemaDeCobrancas();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void criarCurso(Scanner scanner) {
        System.out.print("Digite o nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o total de créditos do curso: ");
        int creditosTotais = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Curso novoCurso = new Curso(nome, creditosTotais);
        dados.getCursos().add(novoCurso);
        System.out.println("Curso criado com sucesso.");
    }

    private static void gerarCurriculoSemestral() {
        System.out.println("Gerando Currículo Semestral...");

        for (Curso curso : dados.getCursos()) {
            System.out.println("Curso: " + curso.getNome());
            System.out.println("Créditos Totais: " + curso.getCreditosTotais());
            System.out.println("Disciplinas:");

            for (Disciplina disciplina : curso.getDisciplinas()) {
                System.out.println("- Disciplina: " + disciplina.getNome());
                System.out.println("  Carga Horária: " + disciplina.getCargaHoraria());
                if (disciplina.getProfessor() != null) {
                    System.out.println("  Professor: " + disciplina.getProfessor().getNome());
                }
                System.out.println("  Alunos Matriculados:");
                for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                    System.out.println("  - " + aluno.getNome());
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("Currículo Semestral gerado com sucesso.");
    }

    private static void gerenciarDisciplinas(Scanner scanner) {
        System.out.println("Gerenciar Disciplinas:");
        System.out.println("1. Adicionar Disciplina");
        System.out.println("2. Editar Disciplina");
        System.out.println("3. Remover Disciplina");
        System.out.println("4. Voltar");

        int escolha = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (escolha) {
            case 1:
                adicionarDisciplina(scanner);
                break;
            case 2:
                editarDisciplina(scanner);
                break;
            case 3:
                removerDisciplina(scanner);
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void adicionarDisciplina(Scanner scanner) {
        System.out.println("Selecione o curso para adicionar a disciplina:");
        List<Curso> cursos = dados.getCursos();
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNome());
        }
        int escolhaCurso = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (escolhaCurso >= 0 && escolhaCurso < cursos.size()) {
            Curso cursoSelecionado = cursos.get(escolhaCurso);

            System.out.print("Digite o nome da disciplina: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a carga horária da disciplina: ");
            int cargaHoraria = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Disciplina novaDisciplina = new Disciplina(nome, cargaHoraria);
            cursoSelecionado.adicionarDisciplina(novaDisciplina);
            System.out.println("Disciplina adicionada com sucesso ao curso " + cursoSelecionado.getNome());
        } else {
            System.out.println("Curso inválido. Operação cancelada.");
        }
    }

    private static void editarDisciplina(Scanner scanner) {
        System.out.print("Digite o nome da disciplina a ser editada: ");
        String nome = scanner.nextLine();
        Disciplina disciplina = null;
        for (Curso curso : dados.getCursos()) {
            disciplina = curso.getDisciplinaPorNome(nome);
            if (disciplina != null) {
                break;
            }
        }

        if (disciplina != null) {
            System.out.print("Digite o novo nome da disciplina: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite a nova carga horária da disciplina: ");
            int novaCargaHoraria = scanner.nextInt();
            scanner.nextLine(); // consume newline

            disciplina.setNome(novoNome);
            disciplina.setCargaHoraria(novaCargaHoraria);
            System.out.println("Disciplina atualizada com sucesso.");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private static void removerDisciplina(Scanner scanner) {
        System.out.print("Digite o nome da disciplina a ser removida: ");
        String nome = scanner.nextLine();
        Disciplina disciplina = null;
        for (Curso curso : dados.getCursos()) {
            disciplina = curso.getDisciplinaPorNome(nome);
            if (disciplina != null) {
                curso.removerDisciplina(disciplina);
                System.out.println("Disciplina removida com sucesso.");
                return;
            }
        }
        System.out.println("Disciplina não encontrada.");
    }

    private static void alocarProfessor(Scanner scanner) {
        System.out.print("Digite o nome da disciplina: ");
        String nome = scanner.nextLine();
        Disciplina disciplina = null;
        for (Curso curso : dados.getCursos()) {
            disciplina = curso.getDisciplinaPorNome(nome);
            if (disciplina != null) {
                break;
            }
        }

        if (disciplina != null) {
            System.out.print("Digite o ID do professor a ser alocado: ");
            String idProfessor = scanner.nextLine();
            Professor professor = dados.getProfessor();

            if (professor != null && professor.getId().equals(idProfessor)) {
                disciplina.setProfessor(professor);
                System.out.println("Professor alocado à disciplina com sucesso.");
            } else {
                System.out.println("Professor não encontrado.");
            }
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private static void notificarSistemaDeCobrancas() {
        System.out.println("Notificando sistema de cobranças...");
        for (Aluno aluno : dados.getAlunos()) {
            dados.getSecretaria().notificarSistemaDeCobrancas(aluno);
        }
        System.out.println("Sistema de cobranças notificado com sucesso.");
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("Cadastrar Novo Usuário");
        System.out.println("1. Cadastrar Secretaria");
        System.out.println("2. Cadastrar Professor");
        System.out.println("3. Cadastrar Aluno");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opção: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                cadastrarSecretaria(scanner);
                break;
            case 2:
                cadastrarProfessor(scanner);
                break;
            case 3:
                cadastrarAluno(scanner);
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void cadastrarSecretaria(Scanner scanner) {
        System.out.print("Digite o ID da Secretaria: ");
        String id = scanner.nextLine();
        System.out.print("Digite o Nome da Secretaria: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o Email da Secretaria: ");
        String email = scanner.nextLine();
        System.out.print("Digite uma Senha para a Secretaria: ");
        String senha = scanner.nextLine();
        Secretaria novaSecretaria = new Secretaria(id, nome, email, senha);
        dados.setSecretaria(novaSecretaria);
        System.out.println("Secretaria cadastrada com sucesso.");
    }

    private static void cadastrarProfessor(Scanner scanner) {
        System.out.print("Digite o ID do Professor: ");
        String id = scanner.nextLine();
        System.out.print("Digite o Nome do Professor: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o Email do Professor: ");
        String email = scanner.nextLine();
        System.out.print("Digite uma Senha para o Professor: ");
        String senha = scanner.nextLine();
        Professor novoProfessor = new Professor(id, nome, email, senha);
        dados.setProfessor(novoProfessor);
        System.out.println("Professor cadastrado com sucesso.");
    }

    private static void cadastrarAluno(Scanner scanner) {
        System.out.print("Digite a Matrícula do Aluno: ");
        String matricula = scanner.nextLine();
        System.out.print("Digite o Nome do Aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o Email do Aluno: ");
        String email = scanner.nextLine();
        System.out.print("Digite o Telefone do Aluno: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite uma Senha para o Aluno: ");
        String senha = scanner.nextLine();
        Aluno novoAluno = new Aluno(matricula, nome, email, telefone, senha);
        dados.getAlunos().add(novoAluno);
        System.out.println("Aluno cadastrado com sucesso.");
    }

    private static void menuProfessor(Scanner scanner) {
        System.out.println("Bem-vindo ao Menu do Professor!");
        System.out.println("1. Ver Alunos Matriculados nas Minhas Disciplinas");
        System.out.println("2. Voltar");
        System.out.print("Escolha uma opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (escolha) {
            case 1:
                verAlunosMatriculados(scanner);
                break;
            case 2:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void verAlunosMatriculados(Scanner scanner) {
        System.out.println("Ver Alunos Matriculados nas Minhas Disciplinas");

        if (dados.getProfessor() == null) {
            System.out.println("Nenhum professor logado.");
            return;
        }

        for (Curso curso : dados.getCursos()) {
            for (Disciplina disciplina : curso.getDisciplinas()) {
                if (disciplina.getProfessor() != null && disciplina.getProfessor().getId().equals(dados.getProfessor().getId())) {
                    System.out.println("Disciplina: " + disciplina.getNome());
                    System.out.println("Alunos Matriculados:");
                    for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                        System.out.println("- " + aluno.getNome());
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void menuAluno(Scanner scanner, Aluno aluno) {
        System.out.println("Bem-vindo ao Menu do Aluno, " + aluno.getNome() + "!");
        System.out.println("1. Ver Minhas Disciplinas");
        System.out.println("2. Matricular-se em Disciplinas");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (escolha) {
            case 1:
                verMinhasDisciplinas(aluno);
                break;
            case 2:
                matricularDisciplinas(scanner, aluno);
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void verMinhasDisciplinas(Aluno aluno) {
        System.out.println("Minhas Disciplinas:");

        for (Curso curso : dados.getCursos()) {
            for (Disciplina disciplina : curso.getDisciplinas()) {
                if (disciplina.getAlunosMatriculados().contains(aluno)) {
                    System.out.println("Disciplina: " + disciplina.getNome());
                    System.out.println("Professor: " + (disciplina.getProfessor() != null ? disciplina.getProfessor().getNome() : "Nenhum"));
                    System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
                    System.out.println();
                }
            }
        }
    }

    private static void matricularDisciplinas(Scanner scanner, Aluno aluno) {
        System.out.println("Matricular-se em Disciplinas");

        System.out.println("Selecione o curso:");
        List<Curso> cursos = dados.getCursos();
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNome());
        }
        int escolhaCurso = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (escolhaCurso >= 0 && escolhaCurso < cursos.size()) {
            Curso cursoSelecionado = cursos.get(escolhaCurso);

            List<Disciplina> disciplinasEscolhidas = new ArrayList<>();
            System.out.println("Escolha até 5 disciplinas para se matricular:");

            List<Disciplina> disciplinas = cursoSelecionado.getDisciplinas();
            for (int i = 0; i < disciplinas.size(); i++) {
                System.out.println((i + 1) + ". " + disciplinas.get(i).getNome());
            }

            for (int i = 0; i < 5; i++) {
                System.out.print("Escolha a disciplina (0 para terminar): ");
                int escolhaDisciplina = scanner.nextInt() - 1;
                scanner.nextLine(); // consume newline

                if (escolhaDisciplina == -1) {
                    break;
                }

                if (escolhaDisciplina >= 0 && escolhaDisciplina < disciplinas.size()) {
                    Disciplina disciplinaEscolhida = disciplinas.get(escolhaDisciplina);
                    if (!disciplinasEscolhidas.contains(disciplinaEscolhida)) {
                        disciplinasEscolhidas.add(disciplinaEscolhida);
                        disciplinaEscolhida.matricularAluno(aluno);
                    }
                } else {
                    System.out.println("Disciplina inválida. Tente novamente.");
                }
            }

            System.out.println("Matrícula realizada com sucesso.");
        } else {
            System.out.println("Curso inválido. Operação cancelada.");
        }
    }
}
