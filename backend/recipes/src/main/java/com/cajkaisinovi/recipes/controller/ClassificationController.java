package com.cajkaisinovi.recipes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.dto.CreateClassificationDto;
import com.cajkaisinovi.recipes.service.IClassificationService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
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

    @PostMapping(
    		value = "/meal-types",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createMealType(@Valid @RequestBody CreateClassificationDto dto, BindingResult bindingResult){
    	// BAD REQUEST
    	if (bindingResult.hasErrors()) {
    		String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
    	
    		return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
    	}
    	
    	MealType created = this.classificationService.createMealType(dto);
    	
    	//CONFLICT
    	if (created == null) {
    		return new ResponseEntity<>(Map.of("message", "MealType already exists"), HttpStatus.CONFLICT);
    	}
    	
    	// CREATED
    	return new ResponseEntity<>(created, HttpStatus.CREATED);
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