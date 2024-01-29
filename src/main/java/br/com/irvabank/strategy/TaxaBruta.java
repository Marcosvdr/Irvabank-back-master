package br.com.irvabank.strategy;

public class TaxaBruta implements TaxaCalculoStrategy {
    private final Double taxa;

    public TaxaBruta(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public Double calcular(double valor) {
        return taxa;
    }
}
