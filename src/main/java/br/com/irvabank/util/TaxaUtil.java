package br.com.irvabank.util;

import br.com.irvabank.strategy.TaxaBruta;
import br.com.irvabank.strategy.TaxaCalculoStrategy;
import br.com.irvabank.strategy.TaxaFixa;
import br.com.irvabank.strategy.TaxaPorcentagem;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class TaxaUtil {

    private static final Map<Predicate<Long>, TaxaCalculoStrategy> REGRAS_TAXA = new HashMap<>();

    static {
        REGRAS_TAXA.put(dias -> dias == 0, new TaxaFixa());
        REGRAS_TAXA.put(dias -> dias >= 1 && dias <= 10, new TaxaBruta(12.0));
        REGRAS_TAXA.put(dias -> dias >= 11 && dias <= 20, new TaxaPorcentagem(0.082));
        REGRAS_TAXA.put(dias -> dias >= 21 && dias <= 30, new TaxaPorcentagem(0.069));
        REGRAS_TAXA.put(dias -> dias >= 31 && dias <= 40, new TaxaPorcentagem(0.047));
        REGRAS_TAXA.put(dias -> dias >= 41 && dias <= 50, new TaxaPorcentagem(0.017));
    }

    public static Double calcularValorTaxa(double valor, Timestamp dataAgendamento, Timestamp dataTransferencia) {
        LocalDateTime dtTransferencia = dataTransferencia.toLocalDateTime();
        LocalDateTime dtAgendamento = dataAgendamento.toLocalDateTime();

        long diferencaDias = Duration.between(dtAgendamento, dtTransferencia).toDays();

        return REGRAS_TAXA.entrySet()
                .stream()
                .filter(entry -> entry.getKey().test(diferencaDias))
                .findFirst()
                .map(entry -> entry.getValue().calcular(valor))
                .orElse(null);
    }
}
