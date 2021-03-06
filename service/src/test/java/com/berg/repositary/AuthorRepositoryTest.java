package com.berg.repositary;

import com.berg.entity.Author;
import com.berg.integration.IntegrationTestBase;
import com.berg.util.EntityHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class AuthorRepositoryTest extends IntegrationTestBase {

    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private Author author;

    @BeforeEach
    public void initDb() {
        author = EntityHelper.createAuthor();
    }

    @Test
    void save(){
        authorRepository.save(this.author);
        entityManager.clear();
        var savedAuthor = authorRepository.findByName(author.getName());
        assertTrue(savedAuthor.isPresent());
        assertThat(savedAuthor.get().getName()).isEqualTo(author.getName());
    }

    @Test
    void delete (){
        authorRepository.save(author);
        var maybeAuthor = authorRepository.findById(author.getId());
        assertTrue(maybeAuthor.isPresent());
        maybeAuthor.ifPresent(authorRepository::delete);
        entityManager.flush();
        assertTrue(authorRepository.findById(maybeAuthor.get().getId()).isEmpty());
    }

    @Test
    void checkFindByQueries(){
        var anna = authorRepository.findByName("Anna");
        assertTrue(anna.isPresent());
        assertThat(anna.get().getName()).isEqualTo("Anna");
        var sveta = authorRepository.findBy("ve");
        assertTrue(sveta.isPresent());
        assertThat(sveta.get().getName()).isEqualTo("Sveta");
    }

    @Test
    void checkUpdateName(){
        var sveta = authorRepository.findByName("Sveta");
        authorRepository.updateName("Petya", sveta.map(Author::getId).orElseThrow());
        var petya = authorRepository.findByName("Petya");
        assertNotNull(petya);
        assertThat(petya.get().getName()).isEqualTo("Petya");
    }

    @Test
    void checkUpdateNameByEntity(){
        var sveta = authorRepository.findByName("Sveta");
        authorRepository.updateName("Petya", sveta.map(Author::getId).orElseThrow());
        var petya = authorRepository.findByName("Petya");
        assertNotNull(petya);
        assertThat(petya.get().getName()).isEqualTo("Petya");
    }
}
