package com.berg.repositary;

import com.berg.entity.CategoryRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryRecipe, Long> {

}
