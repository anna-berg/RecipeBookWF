package com.berg.Repositary;

import com.berg.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

        Optional<Author> findByName(String name);

        @Query("select a from Author a" +
                " where a.name like %:fragment%")
        Optional<Author> findBy(String fragment);

        @Modifying(clearAutomatically = true)
        @Query("update Author a " +
                "set a.name = :name" +
                " where a.id = :id")
        void updateName(String name, Long id);

}
