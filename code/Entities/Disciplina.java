package Entities;
import java.util.ArrayList;
import java.util.List;



public class Disciplina {
    private String codigo;
    private String nome;
    private int creditos;
    private Professor professor;
    private List<Aluno> alunos;

    public Disciplina(String codigo, String nome, int creditos, Professor professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.creditos = creditos;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        System.out.println("Stub: " + aluno.getNome());
        this.alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        System.out.println("Stub: " + aluno.getNome());   
        this.alunos.remove(aluno);
    }

    public int consultarVagas() {
        System.out.println("Stub: ");

        return 60 - this.alunos.size();
    }
}
