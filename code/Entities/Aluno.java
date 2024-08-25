package Entities;
import java.util.List;
import java.util.ArrayList;

public class Aluno {
    private String matricula;
    private String nome;
    private String email;
    private String telefone;

    public Aluno(String matricula, String nome, String email, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void realizarMatricula(Disciplina disciplina) {
    }

    public void cancelarMatricula(Disciplina disciplina) {
        
    }

    public List<Disciplina> consultarDisciplinas() {
        
        return new ArrayList<>();
    }
}
