package model;

import java.time.LocalDate;

public class Entrega {
    private Produtor produtor;
    private ProdutoAgricola produto;
    private double quantidade;
    private LocalDate data;
    private Calculavel precificacao;

    public Entrega(Produtor produtor, ProdutoAgricola produto, double quantidade, Calculavel precificacao) {
        this.produtor = produtor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precificacao = precificacao;
        this.data = LocalDate.now();
    }

    public Produtor getProdutor() { return produtor; }
    public ProdutoAgricola getProduto() { return produto; }
    public double getQuantidade() { return quantidade; }
    public LocalDate getData() { return data; }
    
    public double calcularValor() {
        return precificacao.calcularValor(quantidade, produto.getPrecoReferencia());
    }
}
