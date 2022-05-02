package com.berg.dao;

import com.berg.entity.User;
import com.berg.util.HibernateTestUtil;
import com.berg.util.TestDataImporter;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
class UserDaoTest {

    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
    private final UserDao userDao = UserDao.getInstance();

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> results = userDao.findAll(session);
        assertThat(results).hasSize(2);

        List<String> names = results.stream().map(User::getName).collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("Ivan", "Sveta");

        session.getTransaction().commit();
    }

    @Test
    void findByName(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var user = userDao.findAllByName(session, "Sveta");
        assertThat(user).hasSize(1);
        assertThat(user.get(0).getName()).isEqualTo("Sveta");

        session.getTransaction().commit();
    }
}

