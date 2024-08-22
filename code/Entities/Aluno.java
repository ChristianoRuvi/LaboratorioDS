public class Aluno {
    private String matricula;
    private String nome;
    private String email;
    private String telefone;

    // Constructor
    public Aluno(String matricula, String nome, String email, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters and Setters
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

    // Methods to manage enrollment in disciplines
    public void realizarMatricula(Disciplina disciplina) {
        // Logic to enroll in a discipline
    }

    public void cancelarMatricula(Disciplina disciplina) {
        // Logic to cancel enrollment in a discipline
    }

    public List<Disciplina> consultarDisciplinas() {
        // Logic to consult enrolled disciplines
        return new ArrayList<>();
    }
}
