package model;

public class PrecificacaoPadrao implements Calculavel {
    @Override
    public double calcularValor(double quantidade, double precoReferencia) {
        return quantidade * precoReferencia;
    }
}
