package com.berg.integration.dao;

import com.berg.entity.GroupDay;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class GroupDayRepository extends RepositoryBase<Long, GroupDay>{

    public GroupDayRepository(EntityManager entityManager) {
        super(GroupDay.class, entityManager);
    }
}
