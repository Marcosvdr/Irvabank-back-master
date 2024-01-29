package br.com.irvabank.service;

import br.com.irvabank.dto.ClienteDTO;
import br.com.irvabank.exception.ApiException;
import br.com.irvabank.model.ClienteEntity;
import br.com.irvabank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IService<ClienteDTO, ClienteEntity> {

    @Autowired
    private ClienteRepository repository;
    @Override
    public ClienteEntity insert(ClienteDTO clienteDTO) throws ApiException {
        ClienteEntity cliente = repository.findByNumeroConta(clienteDTO.getNumeroConta());

        if (cliente == null) {
            cliente = new ClienteEntity();
            cliente.setNome(clienteDTO.getNome());
            cliente.setNumeroConta(clienteDTO.getNumeroConta());
            cliente.setActive(true);
            cliente = repository.save(cliente);
        } else {
            throw new ApiException(400, "Cliente já está registrado com esse numero de conta");
        }

        return cliente;
    }

    @Override
    public ClienteEntity update(ClienteDTO dto) throws ApiException {
        ClienteEntity entity = getById(dto.getId());

        ClienteEntity cliente = repository.findByNumeroConta(dto.getNumeroConta());

        if(cliente == null || cliente.getId() == entity.getId()) {

            entity.setNome(dto.getNome());
            entity.setNumeroConta(dto.getNumeroConta());

            cliente = repository.save(entity);

            return cliente;
        } else {
            throw new ApiException(401, "Já existe outro cliente com esse numero de conta");
        }
    }

    @Override
    public ClienteEntity getById(Integer id) throws ApiException {
        Optional<ClienteEntity> optional = repository.findById(id);

        if(optional.isPresent()) {
            return optional.get();
        }else {
            throw new ApiException(401, "Cliente nao encontrado");
        }
    }

    @Override
    public ClienteEntity delete(Integer id) throws ApiException {
        ClienteEntity entity = getById(id);
        repository.delete(entity);
        return null;
    }

    @Override
    public ClienteEntity activate(Integer id) throws ApiException {
        ClienteEntity entity = getById(id);
        entity.setActive(false);
        return repository.save(entity);
    }

    @Override
    public Page<ClienteEntity> findAll(Pageable paging) throws ApiException {
        return repository.findAll(paging);
    }
}
