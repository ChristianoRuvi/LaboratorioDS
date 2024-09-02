package Entities;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String matricula;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Curso cursoMatriculado;
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String matricula, String nome, String email, String telefone, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.disciplinasMatriculadas = new ArrayList<>();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

    public Curso getCursoMatriculado() {
        return cursoMatriculado;
    }

    public void matricularCurso(Curso curso) {
        this.cursoMatriculado = curso;
        this.disciplinasMatriculadas.clear(); // Limpa as disciplinas ao trocar de curso
    }

    public void matricularDisciplina(Disciplina disciplina) {
        if (disciplinasMatriculadas.size() < 5) {
            this.disciplinasMatriculadas.add(disciplina);
        } else {
            System.out.println("Você já está matriculado no número máximo de disciplinas.");
        }
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula='" + matricula + '/' +
                ", nome='" + nome + '/' +
                ", email='" + email + '/' +
                ", telefone='" + telefone + '/' +
                ", senha='" + senha + '/' +
                ", cursoMatriculado=" + (cursoMatriculado != null ? cursoMatriculado.getNome() : "Nenhum") +
                ", disciplinasMatriculadas=" + disciplinasMatriculadas.size() +
                '}';
    }
}