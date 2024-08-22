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
            total += disciplina.getCreditos();
        }
        return total;
    }
}
