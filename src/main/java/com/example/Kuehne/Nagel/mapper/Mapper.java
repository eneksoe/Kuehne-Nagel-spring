package com.example.Kuehne.Nagel.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDtos(List<E> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
