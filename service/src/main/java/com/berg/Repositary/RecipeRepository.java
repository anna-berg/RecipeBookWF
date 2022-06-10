package com.berg.Repositary;

import com.berg.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("select r from Recipe r" +
            " where r.categoryRecipe.category = :category")
    List<Recipe> findAllByCategory(String category);

    @Query("select r" +
            " from Recipe r" +
            " where r.author.name = :author")
    List<Recipe> findAllByAuthor(String author);

    @Query("select distinct r" +
            " from Recipe r" +
            " join fetch r.products p" +
            " where p.id in (:productId)")
    List<Recipe> findAllByProduct(Long ... productId);

    Page<Recipe> findAllBy(Pageable pageable);
}
