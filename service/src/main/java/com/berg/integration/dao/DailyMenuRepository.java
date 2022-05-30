package com.berg.integration.dao;

import com.berg.entity.DailyMenu;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DailyMenuRepository extends RepositoryBase<Long, DailyMenu> {

    public DailyMenuRepository(EntityManager entityManager) {
        super(DailyMenu.class, entityManager);
    }
}
