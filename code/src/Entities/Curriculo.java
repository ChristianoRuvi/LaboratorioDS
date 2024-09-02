package Entities;

import java.util.ArrayList;
import java.util.List;

public class Curriculo {
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinas;

    public Curriculo(String nome, int creditosTotais) {
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditosTotais() {
        return creditosTotais;
    }

    public void setCreditosTotais(int creditosTotais) {
        this.creditosTotais = creditosTotais;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public int calcularCreditosTotais() {
        int total = 0;
        for (Disciplina disciplina : disciplinas) {
            total += disciplina.getCargaHoraria();
        }
        return total;
    }

    
    public void gerarCurriculoSemestral(List<Aluno> alunos) {
        System.out.println("Gerando Currículo Semestral...");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("Disciplina: " + disciplina.getNome());
            System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
            System.out.println("Alunos Matriculados: ");
            for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                System.out.println("- " + aluno.getNome());
            }
            System.out.println();
        }
        System.out.println("Currículo Semestral gerado com sucesso.");
    }
}
