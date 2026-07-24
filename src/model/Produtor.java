package model;

public class Produtor {
    private String nome;
    private String comunidade;
    private String propriedade;

    public Produtor(String nome, String comunidade, String propriedade) {
        this.nome = nome;
        this.comunidade = comunidade;
        this.propriedade = propriedade;
    }

    public String getNome() { return nome; }
    public String getComunidade() { return comunidade; }
    public String getPropriedade() { return propriedade; }
}
