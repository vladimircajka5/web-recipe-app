package com.cajkaisinovi.recipes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cajkaisinovi.recipes.domain.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

	boolean existsByNameIgnoreCase(String name);
	Page<IngredientCategory> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
