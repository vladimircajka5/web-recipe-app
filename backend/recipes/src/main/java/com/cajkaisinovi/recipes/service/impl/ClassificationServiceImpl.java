package com.cajkaisinovi.recipes.service.impl;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.repository.AllergenRepository;
import com.cajkaisinovi.recipes.repository.DietaryTypeRepository;
import com.cajkaisinovi.recipes.repository.DishTypeRepository;
import com.cajkaisinovi.recipes.repository.IngredientCategoryRepository;
import com.cajkaisinovi.recipes.repository.MealTypeRepository;
import com.cajkaisinovi.recipes.service.IClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements IClassificationService {

    @Autowired
    private MealTypeRepository mealTypeRepository;

    @Autowired
    private DishTypeRepository dishTypeRepository;

    @Autowired
    private DietaryTypeRepository dietaryTypeRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private AllergenRepository allergenRepository;

    @Override
    public List<MealType> getMealTypes() {

        // OK
        return this.mealTypeRepository.findAll();
    }

    @Override
    public List<DishType> getDishTypes() {

        // OK
        return this.dishTypeRepository.findAll();
    }

    @Override
    public List<DietaryType> getDietaryTypes() {

        // OK
        return this.dietaryTypeRepository.findAll();
    }

    @Override
    public List<IngredientCategory> getIngredientCategories() {

        // OK
        return this.ingredientCategoryRepository.findAll();
    }

    @Override
    public List<Allergen> getAllergens() {

        // OK
        return this.allergenRepository.findAll();
    }
}