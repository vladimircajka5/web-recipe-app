package com.cajkaisinovi.recipes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.dto.CreateClassificationDto;
import com.cajkaisinovi.recipes.dto.PageResponseDto;
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
    
    @GetMapping(value = "/admin/meal-types",produces = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<?> searchMealTypes(
    	        @RequestParam(name = "search", defaultValue = "") String search,
    	        @RequestParam(name = "page", defaultValue = "0") int page,
    	        @RequestParam(name = "size", defaultValue = "10") int size,
    	        @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
    	        @RequestParam(name = "sortDirection", defaultValue = "asc")
    	        String sortDirection) {

    	    // BAD_REQUEST
    	    if (page < 0 || size < 1 || size > 100) {
    	        return new ResponseEntity<>(
    	                Map.of("message","Page must be non-negative and size must be between 1 and 100."),
    	                HttpStatus.BAD_REQUEST);
    	    }

    	    if (!List.of("id", "name").contains(sortBy)) {
    	        return new ResponseEntity<>(
    	                Map.of("message", "Sort field must be id or name."),
    	                HttpStatus.BAD_REQUEST);
    	    }

    	    if (!"asc".equalsIgnoreCase(sortDirection)
    	            && !"desc".equalsIgnoreCase(sortDirection)) {
    	        return new ResponseEntity<>(
    	                Map.of("message", "Sort direction must be asc or desc."),
    	                HttpStatus.BAD_REQUEST);
    	    }

    	    Page<MealType> result = this.classificationService.searchMealTypes(search, page, size, sortBy, sortDirection);

    	    // OK
    	    return new ResponseEntity<>(new PageResponseDto<>(result),HttpStatus.OK);
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
    
    @PostMapping(
    	    value = "/dish-types",
    	    consumes = MediaType.APPLICATION_JSON_VALUE,
    	    produces = MediaType.APPLICATION_JSON_VALUE
    	)
    	public ResponseEntity<?> createDishType(@Valid @RequestBody CreateClassificationDto dto,BindingResult bindingResult) {
    	    // BAD_REQUEST
    	    if (bindingResult.hasErrors()) {
    	        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();

    	        return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
    	    }

    	    DishType created = this.classificationService.createDishType(dto);

    	    // CONFLICT
    	    if (created == null) {
    	        return new ResponseEntity<>(Map.of("message", "Dish type with this name already exists."),HttpStatus.CONFLICT);
    	    }

    	    // CREATED
    	    return new ResponseEntity<>(created, HttpStatus.CREATED);
    	}
    
    @PostMapping(
    	    value = "/dietary-types",
    	    consumes = MediaType.APPLICATION_JSON_VALUE,
    	    produces = MediaType.APPLICATION_JSON_VALUE
    	)
    	public ResponseEntity<?> createDietaryType(@Valid @RequestBody CreateClassificationDto dto,BindingResult bindingResult) {
    	    // BAD_REQUEST
    	    if (bindingResult.hasErrors()) {
    	        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();

    	        return new ResponseEntity<>(Map.of("message", message),HttpStatus.BAD_REQUEST);
    	    }

    	    DietaryType created = this.classificationService.createDietaryType(dto);

    	    // CONFLICT
    	    if (created == null) {
    	        return new ResponseEntity<>(
    	                Map.of("message", "Dietary type with this name already exists."),
    	                HttpStatus.CONFLICT);
    	    }

    	    // CREATED
    	    return new ResponseEntity<>(created, HttpStatus.CREATED);
    	}
    
    @PostMapping(
    	    value = "/ingredient-categories",
    	    consumes = MediaType.APPLICATION_JSON_VALUE,
    	    produces = MediaType.APPLICATION_JSON_VALUE
    	)
    	public ResponseEntity<?> createIngredientCategory(@Valid @RequestBody CreateClassificationDto dto,BindingResult bindingResult) {
    	    // BAD_REQUEST
    	    if (bindingResult.hasErrors()) {
    	        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();

    	        return new ResponseEntity<>(Map.of("message", message),HttpStatus.BAD_REQUEST);
    	    }

    	    IngredientCategory created = this.classificationService.createIngredientCategory(dto);

    	    // CONFLICT
    	    if (created == null) {
    	        return new ResponseEntity<>(
    	                Map.of("message", "Ingredient category with this name already exists."),
    	                HttpStatus.CONFLICT);
    	    }

    	    // CREATED
    	    return new ResponseEntity<>(created, HttpStatus.CREATED);
    	}
}