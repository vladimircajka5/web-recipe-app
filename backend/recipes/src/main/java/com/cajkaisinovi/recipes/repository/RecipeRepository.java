package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
