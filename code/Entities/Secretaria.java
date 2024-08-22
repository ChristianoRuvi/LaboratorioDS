import java.util.List;

public class Secretaria {
    private String id;
    private String nome;
    private String email;

    public Secretaria(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void gerenciarDisciplinas() {
        // Logic to manage disciplines
    }

    public void gerenciarProfessores() {
        // Logic to manage professors
    }

    public void gerenciarAlunos() {
        // Logic to manage students
    }

    public Curriculo gerarCurriculoSemestral() {
        return new Curriculo();
    }

    public void notificarSistemaDeCobrancas(Aluno aluno) {
        // Logic to notify the billing system
    }
}
