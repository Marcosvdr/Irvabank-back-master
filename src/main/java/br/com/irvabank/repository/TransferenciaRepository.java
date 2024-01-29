package br.com.irvabank.repository;

import br.com.irvabank.model.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {
}
