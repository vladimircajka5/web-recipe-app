package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Pronalazi Recept Po Id-u Autora Njegovog
    List<Recipe> findByAuthorId(Long authorId);

    // Vraca broj recepata koji pripadaju nekom autoru
    long countByAuthorId(Long authorId);
}
