package com.berg.dao;

import com.berg.entity.GroupDay;

import javax.persistence.EntityManager;

public class GroupDayRepository extends RepositoryBase<Long, GroupDay>{

    public GroupDayRepository(EntityManager entityManager) {
        super(GroupDay.class, entityManager);
    }
}
