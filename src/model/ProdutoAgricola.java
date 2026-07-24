package model;

public class ProdutoAgricola {
    private String nome;
    private String unidadeMedida;
    private double precoReferencia;

    public ProdutoAgricola(String nome, String unidadeMedida, double precoReferencia) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.precoReferencia = precoReferencia;
    }

    public String getNome() { return nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public double getPrecoReferencia() { return precoReferencia; }
}
