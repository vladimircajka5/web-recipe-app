package com.cajkaisinovi.recipes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cajkaisinovi.recipes.domain.DietaryType;

public interface DietaryTypeRepository extends JpaRepository<DietaryType, Long> {

	boolean existsByNameIgnoreCase(String name);
	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
	Page<DietaryType> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
