package com.management.fresher.mapper;

public interface Mapper<D, E> {
    E DtoToEntity(D d);
    D EntityToDto(E e);
}
