package com.cajkaisinovi.recipes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cajkaisinovi.recipes.domain.MealType;

public interface MealTypeRepository extends JpaRepository<MealType, Long> {
	
	boolean existsByNameIgnoreCase(String name);
	Page<MealType> findByNameContainingIgnoreCase(String name, Pageable pagable);
}
