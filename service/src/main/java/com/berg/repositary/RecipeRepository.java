package com.berg.repositary;

import com.berg.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, FilterRecipeRepositary {

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
    List<Recipe> findAllByProducts(Long ... productId);

    @Query("select r" +
            " from Recipe r " +
            "join fetch r.products p " +
            "where p.name like :name")
    Optional<Recipe> findByProductName(String name);
}
