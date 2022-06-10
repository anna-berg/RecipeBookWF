package com.berg.Repositary;

import com.berg.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }
}
