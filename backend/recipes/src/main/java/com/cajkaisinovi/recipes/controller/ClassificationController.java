package com.cajkaisinovi.recipes.controller;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.service.IClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassificationController {

    @Autowired
    private IClassificationService classificationService;

    @GetMapping(value = "/meal-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MealType>> getMealTypes() {

        List<MealType> all = this.classificationService.getMealTypes();

        // OK
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/dish-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishType>> getDishTypes() {

        List<DishType> all = this.classificationService.getDishTypes();

        // OK
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/dietary-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DietaryType>> getDietaryTypes() {

        List<DietaryType> all = this.classificationService.getDietaryTypes();

        // OK
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/ingredient-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IngredientCategory>> getIngredientCategories() {

        List<IngredientCategory> all = this.classificationService.getIngredientCategories();

        // OK
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/allergens", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allergen>> getAllergens() {

        List<Allergen> all = this.classificationService.getAllergens();

        // OK
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}