package com.cajkaisinovi.recipes.service;

import com.cajkaisinovi.recipes.dto.RecipeDto;

import java.util.List;

public interface IRecipeService {

    List<RecipeDto> getAll();

    RecipeDto findById(Long recipeId);

    List<RecipeDto> findByAuthorId(Long userId);
}