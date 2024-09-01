package Entities;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void matricularAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '/' +
                ", cargaHoraria=" + cargaHoraria +
                ", professor=" + (professor != null ? professor.getNome() : "Nenhum") +
                ", alunosMatriculados=" + alunosMatriculados.size() +
                '}';
    }
}
