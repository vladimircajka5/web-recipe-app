package com.cajkaisinovi.recipes.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.dto.CreateClassificationDto;

public interface IClassificationService {

    List<MealType> getMealTypes();
    MealType createMealType(CreateClassificationDto dto);
    Page<MealType> searchMealTypes(String search,int page,int size,String sortBy,String sortDirection);

    List<DishType> getDishTypes();
    DishType createDishType(CreateClassificationDto dto);

    List<DietaryType> getDietaryTypes();
    DietaryType createDietaryType(CreateClassificationDto dto);

    List<IngredientCategory> getIngredientCategories();
    IngredientCategory createIngredientCategory(CreateClassificationDto dto);

    List<Allergen> getAllergens();
}