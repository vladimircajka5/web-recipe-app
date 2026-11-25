package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

	boolean existsByNameIgnoreCase(String name);
	
}
