package com.jmp.cloud.service.repository;

import com.jmp.dto.Subscription;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CrudRepository<T, K> {
    private Map<K, T> table = new HashMap<>();

    protected Map<K, T> getTable()  {
        return table;
    }

    public abstract T save(T entity);

    public T findOne(K id) {
        return table.get(id);
    }

    public List<T> findAll() {
        return table.values()
            .stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                x -> Collections.unmodifiableList(x)));
    }
}
