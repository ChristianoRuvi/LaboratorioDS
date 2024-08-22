package Entities;
import java.util.List;


public class SistemaDeCobrancas {

    public void cobrar(Aluno aluno, List<Disciplina> disciplinas) {
        double total = 0.0;
        for (Disciplina disciplina : disciplinas) {
            total += disciplina.getCreditos() * 100;
        }
        System.out.println("Cobrando " + aluno.getNome() + " um total de R$ " + total);
    }
}
