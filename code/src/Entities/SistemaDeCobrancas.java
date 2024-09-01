
package Entities;
import java.util.List;

public class SistemaDeCobrancas {
    public void cobrar(Aluno aluno, List<Disciplina> disciplinas) {
        int totalCreditos = 0;
        for (Disciplina disciplina : disciplinas) {
            totalCreditos += disciplina.getCargaHoraria();
        }
        System.out.println("Cobrando " + totalCreditos + " créditos de " + aluno.getNome());
    }
}
