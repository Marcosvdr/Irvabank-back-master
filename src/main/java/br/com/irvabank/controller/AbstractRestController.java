package br.com.irvabank.controller;

import java.io.Serializable;

import br.com.irvabank.exception.ApiException;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.irvabank.service.IService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
public class AbstractRestController <DTO extends Serializable, Entity extends  Serializable> {

    @Autowired
    protected IService<DTO, Entity> service;

    @Operation(summary = "Insert entity data")
    @PostMapping(value = "/insert")
    public Entity insert(@RequestBody @Valid DTO dto){
        return service.insert(dto);
    }

    @Operation(summary = "Update entity data")
    @PutMapping(value = "/update")
    public Entity update(@RequestBody @Valid DTO dto) throws ApiException {
        return service.update(dto);
    }

    @Operation(summary = "Disable an element")
    @DeleteMapping(value = "/delete/{id}")
    public Entity delete(@PathVariable Integer id) throws ApiException {
        return service.delete(id);
    }

    @Operation(summary = "List all registered items")
    @GetMapping(value = "/findAll")
    public Page<Entity> findAll(@ParameterObject Pageable pageable) throws ApiException {
        return service.findAll(pageable);
    }

    @Operation(summary = "Get a entity by its id")
    @GetMapping(value = "/find/{id}")
    public Entity findById(@Parameter(description = "id of entity to be searched") @PathVariable Integer id) throws ApiException {
        return service.getById(id);
    }

    @Operation(summary = "Reactivate deleted entity")
    @PutMapping(value = "/activate")
    public Entity activate(@RequestBody @Valid Integer id) throws ApiException {
        return service.activate(id);
    }
}
