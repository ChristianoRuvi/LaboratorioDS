
import Entities.Aluno;
import Entities.Curso;
import Entities.Disciplina;
import Entities.Professor;
import Entities.Curriculo;
import Entities.SistemaDeCobrancas;

public class Main {
    public static void main(String[] args) {
        Professor professor1 = new Professor("1", "Professor A", "prof.a@universidade.com");
        Professor professor2 = new Professor("2", "Professor B", "prof.b@universidade.com");

        Disciplina disciplina1 = new Disciplina("MAT101", "Matemática", 4, professor1);
        Disciplina disciplina2 = new Disciplina("FIS101", "Física", 3, professor2);

       
        Aluno aluno1 = new Aluno("001", "Aluno Um", "aluno.um@universidade.com", "123456789");
        Aluno aluno2 = new Aluno("002", "Aluno Dois", "aluno.dois@universidade.com", "987654321");

        
        aluno1.realizarMatricula(disciplina1);
        aluno2.realizarMatricula(disciplina2);

        disciplina1.adicionarAluno(aluno1);
        disciplina2.adicionarAluno(aluno2);

        Curso curso = new Curso("Engenharia", 8);
        curso.adicionarDisciplina(disciplina1);
        curso.adicionarDisciplina(disciplina2);

        
        Curriculo curriculo = new Curriculo("2024.1");
        curriculo.adicionarDisciplina(disciplina1);
        curriculo.adicionarDisciplina(disciplina2);

    
        SistemaDeCobrancas sistemaDeCobrancas = new SistemaDeCobrancas();
        sistemaDeCobrancas.cobrar(aluno1, curriculo.getDisciplinas());
        sistemaDeCobrancas.cobrar(aluno2, curriculo.getDisciplinas());

       
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Total de créditos do curso: " + curso.calcularCreditosTotais());
        System.out.println("Currículo do semestre " + curriculo.getSemestre() + " tem " + curriculo.calcularTotalCreditos() + " créditos.");

       
        System.out.println("\nDisciplinas e alunos matriculados:");
        for (Disciplina disciplina : curso.getDisciplinas()) {
            System.out.println("Disciplina: " + disciplina.getNome());
            for (Aluno aluno : disciplina.getAlunos()) {
                System.out.println(" - Aluno: " + aluno.getNome());
            }
        }
    }
}
