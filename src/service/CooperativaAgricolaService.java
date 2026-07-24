package service;

import model.*;
import exception.QuantidadeInvalidaException;
import java.util.*;

public class CooperativaAgricolaService {
    private List<Produtor> produtores = new ArrayList<>();
    private List<ProdutoAgricola> produtos = new ArrayList<>();
    private List<Entrega> entregas = new ArrayList<>();

    public void cadastrarProdutor(Produtor p) { produtores.add(p); }
    public void removerProdutor(Produtor p) { produtores.remove(p); }
    public List<Produtor> getProdutores() { return produtores; }
    
    public Produtor buscarProdutorPorNome(String nome) {
        for (Produtor p : produtores) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    public void cadastrarProduto(ProdutoAgricola p) { produtos.add(p); }
    public void removerProduto(ProdutoAgricola p) { produtos.remove(p); }
    public List<ProdutoAgricola> getProdutos() { return produtos; }
    
    public ProdutoAgricola buscarProdutoPorNome(String nome) {
        for (ProdutoAgricola p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    public List<Entrega> getEntregas() { return entregas; }

    public void registrarEntrega(Produtor produtor, ProdutoAgricola produto, double quantidade, boolean institucional) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Erro: A quantidade ou preço informados são inválidos (devem ser maiores que zero).");
        }
        Calculavel precificacao = institucional ? new PrecificacaoInstitucional() : new PrecificacaoPadrao();
        Entrega entrega = new Entrega(produtor, produto, quantidade, precificacao);
        entregas.add(entrega);
    }

    public double calcularReceitaPorProdutor(Produtor produtor) {
        double total = 0;
        for (Entrega e : entregas) {
            if (e.getProdutor().equals(produtor)) {
                total += e.calcularValor();
            }
        }
        return total;
    }

    public List<Map.Entry<Produtor, Double>> rankingPorVolume() {
        Map<Produtor, Double> volumePorProdutor = new HashMap<>();
        for (Entrega e : entregas) {
            volumePorProdutor.put(e.getProdutor(), volumePorProdutor.getOrDefault(e.getProdutor(), 0.0) + e.getQuantidade());
        }
        List<Map.Entry<Produtor, Double>> ranking = new ArrayList<>(volumePorProdutor.entrySet());
        ranking.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return ranking;
    }

    public Map<String, Double> consolidarEntregasPorProduto() {
        Map<String, Double> consolidador = new HashMap<>();
        for (Entrega e : entregas) {
            String nomeProduto = e.getProduto().getNome();
            consolidador.put(nomeProduto, consolidador.getOrDefault(nomeProduto, 0.0) + e.getQuantidade());
        }
        return consolidador;
    }
}
