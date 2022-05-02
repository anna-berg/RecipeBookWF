package com.berg.dao;

import com.berg.entity.DailyMenu;

import javax.persistence.EntityManager;

public class DailyMenuRepository extends RepositoryBase<Long, DailyMenu> {

    public DailyMenuRepository(EntityManager entityManager) {
        super(DailyMenu.class, entityManager);
    }
}
