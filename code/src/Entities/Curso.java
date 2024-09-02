package Entities;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinas;

    public Curso(String nome, int creditosTotais) {
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getCreditosTotais() {
        return creditosTotais;
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

    
    public Disciplina getDisciplinaPorNome(String nome) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equalsIgnoreCase(nome)) {
                return disciplina;
            }
        }
        return null; 
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                ", creditosTotais=" + creditosTotais +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
