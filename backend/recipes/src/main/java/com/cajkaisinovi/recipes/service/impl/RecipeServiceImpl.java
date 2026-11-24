package com.cajkaisinovi.recipes.service.impl;

import com.cajkaisinovi.recipes.domain.Recipe;
import com.cajkaisinovi.recipes.dto.RecipeDto;
import com.cajkaisinovi.recipes.repository.RecipeRepository;
import com.cajkaisinovi.recipes.repository.UserRepository;
import com.cajkaisinovi.recipes.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RecipeDto> getAll() {

        // OK
        List<Recipe> recipes = this.recipeRepository.findAll();
        return convertToDtoList(recipes);
    }

    @Override
    public RecipeDto findById(Long recipeId) {

        Optional<Recipe> found = this.recipeRepository.findById(recipeId);

        // NOT_FOUND
        if (found.isEmpty()) {
            return null;
        }
        
        Recipe recipe = found.get();
        Double averageRating = recipeRepository.findAverageRatingByRecipeId(recipeId);

        // OK
        return new RecipeDto(recipe,averageRating);
    }

    @Override
    public List<RecipeDto> findByAuthorId(Long userId) {

        // NOT_FOUND
        if (!this.userRepository.existsById(userId)) {
            return null;
        }

        // OK
        List<Recipe> recipes = this.recipeRepository.findByAuthorId(userId);
        return convertToDtoList(recipes);
    }

    private List<RecipeDto> convertToDtoList(List<Recipe> recipes) {
        List<RecipeDto> result = new ArrayList<>();
        
        if(recipes.isEmpty()) {
        	return result;
        }

        for (Recipe recipe : recipes) {
            Double averageRating = recipeRepository.findAverageRatingByRecipeId(recipe.getId());
            result.add(new RecipeDto(recipe, averageRating));
        }

        return result;
    }
    
    @Override
    public List<RecipeDto> getNewest() {
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "creationDate", "id");
        return convertToDtoList(recipeRepository.findAll(newestFirst));
    }
    
    @Override
    public List<RecipeDto> getPopular() {
        LocalDateTime now = LocalDateTime.now();
        return convertToDtoList(recipeRepository.findPopularRecipes(now.minusMonths(1), now));
    }
}