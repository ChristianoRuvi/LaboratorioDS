package Entities;

public class Secretaria {
    private String id;
    private String nome;
    private String email;
    private String senha;

    public Secretaria(String id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void notificarSistemaDeCobrancas(Aluno aluno) {
        System.out.println("Notificando sistema de cobranças para o aluno: " + aluno.getNome());

    }
}
