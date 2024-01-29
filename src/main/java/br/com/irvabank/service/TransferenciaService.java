package br.com.irvabank.service;

import br.com.irvabank.dto.TransferenciaDTO;
import br.com.irvabank.exception.ApiException;
import br.com.irvabank.mapper.TransferenciaMapper;
import br.com.irvabank.model.ClienteEntity;
import br.com.irvabank.model.TransferenciaEntity;
import br.com.irvabank.repository.ClienteRepository;
import br.com.irvabank.repository.TransferenciaRepository;
import br.com.irvabank.util.TaxaUtil;
import br.com.irvabank.validation.TransferenciaCadastroValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService implements IService<TransferenciaDTO, TransferenciaEntity> {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransferenciaCadastroValidation transferenciaCadastroValidation;

    @Override
    public TransferenciaEntity insert(TransferenciaDTO dto) throws ApiException {

        ClienteEntity clienteOrigem = clienteRepository.findByNumeroConta(dto.getContaOrigem());

        TransferenciaEntity entity = TransferenciaMapper.dtoToEntity(dto);
        entity.setCliente(clienteOrigem);
        entity.setTaxa(TaxaUtil.calcularValorTaxa(entity.getValor(),  entity.getDataAgendamento(), entity.getDataTransferencia()));

        validaErros(entity);

        entity = transferenciaRepository.save(entity);
        return entity;
    }

    private void validaErros(TransferenciaEntity entity) {
        Errors errors = new BeanPropertyBindingResult(entity, TransferenciaEntity.class.getName());
        transferenciaCadastroValidation.validate(entity, errors);

        if (errors.hasErrors()) {
            List<String> errorMessages = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());

            throw new ApiException(401, String.join(" ", errorMessages));
        }
    }

    @Override
    public TransferenciaEntity update(TransferenciaDTO transferenciaDTO) throws ApiException {
        return null;
    }

    @Override
    public TransferenciaEntity getById(Integer id) throws ApiException {
        Optional<TransferenciaEntity> optional = transferenciaRepository.findById(id);

        if(optional.isPresent()) {
            return optional.get();
        }else {
            throw new ApiException(401, "Transferência não encontrada");
        }
    }

    @Override
    public TransferenciaEntity delete(Integer id) throws ApiException {
        TransferenciaEntity entity = getById(id);
        transferenciaRepository.delete(entity);
        return null;
    }

    @Override
    public TransferenciaEntity activate(Integer id) throws ApiException {
        return null;
    }

    @Override
    public Page<TransferenciaEntity> findAll(Pageable paging) throws ApiException {
        return transferenciaRepository.findAll(paging);
    }
}
