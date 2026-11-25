package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishTypeRepository extends JpaRepository<DishType, Long> {
	
	boolean existsByNameIgnoreCase(String name);

}
