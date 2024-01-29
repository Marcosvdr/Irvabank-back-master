package br.com.irvabank.repository;

import br.com.irvabank.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    public ClienteEntity findByNumeroConta(String numeroConta);
}
