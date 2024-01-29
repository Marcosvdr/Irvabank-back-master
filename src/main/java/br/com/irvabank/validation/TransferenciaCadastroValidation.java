package br.com.irvabank.validation;


import br.com.irvabank.dto.TransferenciaDTO;
import br.com.irvabank.model.ClienteEntity;
import br.com.irvabank.model.TransferenciaEntity;
import br.com.irvabank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class TransferenciaCadastroValidation implements Validator {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferenciaEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferenciaEntity entity = (TransferenciaEntity) target;

        ClienteEntity origem = clienteRepository.findByNumeroConta(entity.getContaOrigem());
        ClienteEntity destino = clienteRepository.findByNumeroConta(entity.getContaDestino());

        if (Objects.equals(entity.getContaOrigem(), entity.getContaDestino())) errors.reject("Transferência Inválida", "Não é possível realizar transferencia para a propría conta.");
        if (Objects.isNull(origem)) errors.reject("Conta Inválida", "Número da conta que será debitado não está cadastrada.");
        if (Objects.isNull(destino)) errors.reject("Conta Inválida", "Número da conta que será transferido não está cadastrada.");
        if (entity.getValor() <= 0)  errors.reject("Valor Inválido", "Valor a ser transferido não pode ser menor ou igual a zero.");
        if (Objects.isNull(entity.getTaxa())) errors.reject("Transferência Inválida", "Não é possivel aplicar taxa nessa transferência.");
    }
}
