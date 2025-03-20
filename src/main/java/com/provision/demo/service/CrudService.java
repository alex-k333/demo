package com.provision.demo.service;

import com.provision.demo.mapper.BaseMapper;
import com.provision.demo.model.Id;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class CrudService<D, E extends Id<ID>, R, ID> {

    protected abstract BaseMapper<D, E, R> getMapper();

    protected abstract JpaRepository<E, ID> getRepository();

    public List<R> getAll() {
        return getRepository().findAll().stream()
                .map(entity -> getMapper().toResponse(entity))
                .toList();
    }

    public R create(@Valid D dto) {
        E entity = getMapper().toEntity(dto);
        getRepository().save(entity);
        return getMapper().toResponse(entity);
    }

    public R update(@PathVariable("id") ID id, @Valid D dto) {
        E entity = getRepository().findById(id)
                .orElseThrow(EntityNotFoundException::new);
        E updatedEntity = getMapper().toEntity(dto);
        updatedEntity.setId(entity.getId());
        getRepository().save(updatedEntity);
        return getMapper().toResponse(updatedEntity);
    }

    public void delete(@PathVariable("id") ID id) {
        getRepository().deleteById(id);
    }

    public R getById(@PathVariable("id") ID id) {
        return getRepository().findById(id)
                .map(entity -> getMapper().toResponse(entity))
                .orElseThrow(EntityNotFoundException::new);
    }

}
