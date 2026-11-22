package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {

}
