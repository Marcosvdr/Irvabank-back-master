package br.com.irvabank.mapper;

import br.com.irvabank.dto.TransferenciaDTO;
import br.com.irvabank.model.TransferenciaEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class TransferenciaMapper {

    public static TransferenciaEntity dtoToEntity(TransferenciaDTO dto) {
        TransferenciaEntity entity = new TransferenciaEntity();
        entity.setContaOrigem(dto.getContaOrigem());
        entity.setContaDestino(dto.getContaDestino());
        entity.setDataTransferencia(dto.getDataTransferencia());
        entity.setValor(dto.getValor());
        entity.setDataAgendamento(Timestamp.from(Instant.now()));
        return entity;
    }
}
