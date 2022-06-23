package com.berg.repositary;

import com.berg.entity.FavoriteRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {

}
