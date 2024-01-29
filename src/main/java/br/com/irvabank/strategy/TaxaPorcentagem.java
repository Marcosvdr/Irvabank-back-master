package br.com.irvabank.strategy;

public class TaxaPorcentagem implements TaxaCalculoStrategy{
    private final Double porcentagem;

    public TaxaPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    @Override
    public Double calcular(double valor) {
        return porcentagem * valor;
    }
}
