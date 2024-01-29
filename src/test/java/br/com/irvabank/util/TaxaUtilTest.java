package br.com.irvabank.util;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaUtilTest {

    @Test
    void testTaxaDiaria() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now());
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertNotNull(resultado);
        assertEquals(3 + (0.025 * valor), resultado);
    }

    @Test
    void testTaxaFixa() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now().plusDays(5));
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertNotNull(resultado);
        assertEquals(12.0, resultado);
    }

    @Test
    void testTaxaPorcentagem1() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now().plusDays(15));
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertEquals(0.082 * valor, resultado);
    }

    @Test
    void testTaxaPorcentagem2() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now().plusDays(25));
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertNotNull(resultado);
        assertEquals(0.069 * valor, resultado);
    }

    @Test
    void testTaxaPorcentagem3() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now().plusDays(35));
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertNotNull(resultado);
        assertEquals(0.047 * valor, resultado);
    }

    @Test
    void testTaxaPorcentagem4() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now().plusDays(45));
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertNotNull(resultado);
        assertEquals(0.017 * valor, resultado);
    }

    @Test
    void testNullForOutOfRange() {
        double valor = 100.0;
        Timestamp dataTransferencia = Timestamp.valueOf(LocalDateTime.now().plusDays(55));
        Timestamp dataAgendamento = Timestamp.valueOf(LocalDateTime.now());

        Double resultado = TaxaUtil.calcularValorTaxa(valor, dataAgendamento, dataTransferencia);

        assertNull(resultado);
    }
}
