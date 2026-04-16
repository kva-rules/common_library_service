package com.library.common.domain.ports;

import java.util.Optional;
import java.util.List;

public interface RepositoryPort<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}

