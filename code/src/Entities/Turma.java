
package Entities;
import java.util.List;
import java.util.ArrayList;

public class Turma {
    private String semestre;
    private List<Aluno> alunos;
    private Professor professor;
    private List<Disciplina> disciplinas;

    public Turma(String semestre, Professor professor) {
        this.semestre = semestre;
        this.professor = professor;
        this.alunos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }
}
