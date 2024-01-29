package br.com.irvabank.dto;

import java.io.Serializable;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class TransferenciaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull
    private String contaOrigem;
    @NotNull
    private String contaDestino;

    private Timestamp dataAgendamento;
    @NotNull
    private Timestamp dataTransferencia;
    @NotNull
    private Double valor;

    private Double taxa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Timestamp getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Timestamp dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Timestamp getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(Timestamp dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }
}