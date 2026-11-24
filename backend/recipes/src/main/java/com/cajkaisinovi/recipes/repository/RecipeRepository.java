package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Pronalazi Recept Po Id-u Autora Njegovog
    List<Recipe> findByAuthorId(Long authorId);

    // Vraca broj recepata koji pripadaju nekom autoru
    long countByAuthorId(Long authorId);
    
    @Query(value = """
    	    SELECT recipe.*
    	    FROM recipes recipe
    	    JOIN (
    	        SELECT recipe_id, AVG(rating_value) AS monthly_average
    	        FROM ratings
    	        WHERE created_at >= :cutoff AND created_at <= :asOf
    	        GROUP BY recipe_id
    	    ) monthly_ratings ON monthly_ratings.recipe_id = recipe.id
    	    ORDER BY monthly_ratings.monthly_average DESC, recipe.creation_date DESC, recipe.id DESC
    	    """, nativeQuery = true)
    List<Recipe> findPopularRecipes(@Param("cutoff") LocalDateTime cutoff, @Param("asOf") LocalDateTime asOf);
}
