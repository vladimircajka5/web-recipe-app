package com.cajkaisinovi.recipes.service;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.dto.CreateClassificationDto;

import java.util.List;

public interface IClassificationService {

    List<MealType> getMealTypes();

    MealType createMealType(CreateClassificationDto dto);

    List<DishType> getDishTypes();

    List<DietaryType> getDietaryTypes();

    List<IngredientCategory> getIngredientCategories();

    List<Allergen> getAllergens();
}