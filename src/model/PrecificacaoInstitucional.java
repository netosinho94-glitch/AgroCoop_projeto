package model;

public class PrecificacaoInstitucional implements Calculavel {
    private static final double PERCENTUAL_INCENTIVO = 0.08;

    @Override
    public double calcularValor(double quantidade, double precoReferencia) {
        return (quantidade * precoReferencia) * (1 + PERCENTUAL_INCENTIVO);
    }
}
