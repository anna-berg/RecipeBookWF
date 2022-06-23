package com.berg.repositary;

import com.berg.entity.BaseEntity;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    default Optional<E> findById(K id) {
        return findById(id, emptyMap());
    }

    Optional<E> findById(K id, Map<String, Object> properties);
}
