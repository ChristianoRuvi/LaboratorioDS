package Entities;

import java.util.ArrayList;
import java.util.List;

public class DadosUniversidade {
    private Secretaria secretaria;
    private Professor professor;
    private List<Curso> cursos; // Alteração: agora usamos uma lista de cursos
    private List<Aluno> alunos;

    // Construtor com Parâmetros
    public DadosUniversidade(Secretaria secretaria, Professor professor, List<Curso> cursos, List<Aluno> alunos) {
        this.secretaria = secretaria;
        this.professor = professor;
        this.cursos = cursos;
        this.alunos = alunos;
    }

    // Construtor Padrão
    public DadosUniversidade() {
        this.cursos = new ArrayList<>(); // Inicializando lista de cursos
        this.alunos = new ArrayList<>();
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void adicionarCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return "DadosUniversidade{" +
                "secretaria=" + secretaria +
                ", professor=" + professor +
                ", cursos=" + cursos +
                ", alunos=" + alunos +
                '}';
    }
}
