package com.cajkaisinovi.recipes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cajkaisinovi.recipes.domain.DishType;

public interface DishTypeRepository extends JpaRepository<DishType, Long> {
	
	boolean existsByNameIgnoreCase(String name);
	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
	Page<DishType> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
