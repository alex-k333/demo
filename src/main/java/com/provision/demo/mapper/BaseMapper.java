package com.provision.demo.mapper;

public interface BaseMapper<D, E, R> {

    E toEntity(D dto);
    D toDto(E entity);
    R toResponse(E entity);
}
