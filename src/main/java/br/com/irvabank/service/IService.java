package br.com.irvabank.service;

import java.io.Serializable;

import br.com.irvabank.exception.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IService<DTO extends Serializable, Entity extends  Serializable>  {


    public Entity insert(DTO dto) throws ApiException;

    public Entity update(DTO dto) throws ApiException;

    public Entity getById(Integer id) throws ApiException;

    public Entity delete(Integer id) throws ApiException;

    public Entity activate(Integer id) throws ApiException;

    public Page<Entity> findAll(Pageable paging) throws ApiException;

}
