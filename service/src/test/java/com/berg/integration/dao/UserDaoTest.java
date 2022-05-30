package com.berg.integration.dao;

import com.berg.entity.User;
import com.berg.integration.IntegrationTestBase;
import com.berg.integration.annotation.IT;
import com.berg.util.TestDataImporter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class UserDaoTest extends IntegrationTestBase {

    private final UserDao userDao = UserDao.getInstance();
    private final EntityManager entityManager;

    @BeforeEach
    public void initDb() {
        TestDataImporter.importData(entityManager);
    }

    @Test
    void findAll() {
        List<User> results = userDao.findAll(entityManager);
        assertThat(results).hasSize(2);

        List<String> names = results.stream().map(User::getName).collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("Ivan", "Sveta");
    }

    @Test
    void findByName() {
        var user = userDao.findAllByName(entityManager, "Sveta");
        assertThat(user).hasSize(1);
        assertThat(user.get(0).getName()).isEqualTo("Sveta");
    }
}

