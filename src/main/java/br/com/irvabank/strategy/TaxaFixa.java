package br.com.irvabank.strategy;

public class TaxaFixa implements TaxaCalculoStrategy{
    @Override
    public Double calcular(double valor) {
        return 3 + (0.025 * valor);
    }
}
