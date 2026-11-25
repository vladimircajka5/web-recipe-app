package com.cajkaisinovi.recipes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cajkaisinovi.recipes.domain.Allergen;
import com.cajkaisinovi.recipes.domain.DietaryType;
import com.cajkaisinovi.recipes.domain.DishType;
import com.cajkaisinovi.recipes.domain.IngredientCategory;
import com.cajkaisinovi.recipes.domain.MealType;
import com.cajkaisinovi.recipes.dto.CreateClassificationDto;
import com.cajkaisinovi.recipes.repository.AllergenRepository;
import com.cajkaisinovi.recipes.repository.DietaryTypeRepository;
import com.cajkaisinovi.recipes.repository.DishTypeRepository;
import com.cajkaisinovi.recipes.repository.IngredientCategoryRepository;
import com.cajkaisinovi.recipes.repository.MealTypeRepository;
import com.cajkaisinovi.recipes.service.IClassificationService;
import com.cajkaisinovi.recipes.exception.ClassificationConflictException;
import com.cajkaisinovi.recipes.repository.RecipeRepository;

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
    
    @Autowired
    private RecipeRepository recipeRepository;

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
    public List<Allergen> getAllergens() {

        // OK
        return this.allergenRepository.findAll();
    }
    
    @Override
	public DishType createDishType(CreateClassificationDto dto) {
		String name = dto.getName().trim();
		
		// conflict
		if (this.dishTypeRepository.existsByNameIgnoreCase(name)) {
			return null;
		}
		
		DishType dishType = new DishType(name);
		
		// ok
		return this.dishTypeRepository.save(dishType);
	}

    @Override
    public List<DietaryType> getDietaryTypes() {

        // OK
        return this.dietaryTypeRepository.findAll();
    }
    
    
    
    // CREATE
    //____________________________________________________________________________________________
    
    @Override
    public MealType createMealType(CreateClassificationDto dto){
        String name = dto.getName().trim();

        // conflict
        if (this.mealTypeRepository.existsByNameIgnoreCase(name)) {
            return null;
        }

        MealType mealType = new MealType(name);

        // ok
        return this.mealTypeRepository.save(mealType);
    }

    @Override
    public DietaryType createDietaryType(CreateClassificationDto dto) {

        String name = dto.getName().trim();

        // conflict
        if (this.dietaryTypeRepository.existsByNameIgnoreCase(name)) {
            return null;
        }

        DietaryType dietaryType = new DietaryType(name);
        
        // ok
        return this.dietaryTypeRepository.save(dietaryType);
    }

    @Override
    public List<IngredientCategory> getIngredientCategories() {

        // OK
        return this.ingredientCategoryRepository.findAll();
    }
    
    @Override
    public IngredientCategory createIngredientCategory(CreateClassificationDto dto) {

        String name = dto.getName().trim();

        // conflict
        if (this.ingredientCategoryRepository.existsByNameIgnoreCase(name)) {
            return null;
        }

        IngredientCategory ingredientCategory = new IngredientCategory(name);

        // ok
        return this.ingredientCategoryRepository.save(ingredientCategory);
    }
    
    // UPDATE
    //____________________________________________________________________________________________
    
    @Override
    @Transactional
    public MealType updateMealType(Long id, CreateClassificationDto dto) {

        Optional<MealType> found = this.mealTypeRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return null;
        }

        String name = dto.getName().trim();

        // CONFLICT
        if (this.mealTypeRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new ClassificationConflictException(
                    "Meal type with this name already exists.");
        }

        MealType mealType = found.get();
        mealType.setName(name);

        // OK
        return this.mealTypeRepository.saveAndFlush(mealType);
    }

    @Override
    @Transactional
    public DishType updateDishType(Long id, CreateClassificationDto dto) {

        Optional<DishType> found = this.dishTypeRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return null;
        }

        String name = dto.getName().trim();

        // CONFLICT
        if (this.dishTypeRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new ClassificationConflictException("Dish type with this name already exists.");
        }

        DishType dishType = found.get();
        dishType.setName(name);

        // OK
        return this.dishTypeRepository.saveAndFlush(dishType);
    }
    
    @Override
    @Transactional
    public DietaryType updateDietaryType(Long id, CreateClassificationDto dto) {

        Optional<DietaryType> found = this.dietaryTypeRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return null;
        }

        String name = dto.getName().trim();

        // CONFLICT
        if (this.dietaryTypeRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new ClassificationConflictException("Dietary type with this name already exists.");
        }

        DietaryType dietaryType = found.get();
        dietaryType.setName(name);

        // OK
        return this.dietaryTypeRepository.saveAndFlush(dietaryType);
    }
    
    @Override
    @Transactional
    public IngredientCategory updateIngredientCategory(Long id, CreateClassificationDto dto) {

        Optional<IngredientCategory> found = this.ingredientCategoryRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return null;
        }

        String name = dto.getName().trim();

        // CONFLICT
        if (this.ingredientCategoryRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
        	throw new ClassificationConflictException("Ingredient Category with this name already exists.");
        }

        IngredientCategory ingredientCategory= found.get();
        ingredientCategory.setName(name);

        // OK
        return this.ingredientCategoryRepository.saveAndFlush(ingredientCategory);
    }
    
    
    // DELETE
    //____________________________________________________________________________________________

    @Override
    @Transactional
    public boolean deleteMealType(Long id) {

        Optional<MealType> found = this.mealTypeRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return false;
        }

        // CONFLICT
        if (this.recipeRepository.existsByMealTypeId(id)) {
            throw new ClassificationConflictException(
                    "Meal type cannot be deleted because it is used by recipes.");
        }

        this.mealTypeRepository.delete(found.get());
        this.mealTypeRepository.flush();

        // DELETED
        return true;
    }
    
    @Override
    @Transactional
    public boolean deleteDishType(Long id) {

        Optional<DishType> found = this.dishTypeRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return false;
        }

        // CONFLICT
        if (this.recipeRepository.existsByDishTypeId(id)) {
            throw new ClassificationConflictException(
                    "Dish type cannot be deleted because it is used by recipes.");
        }

        this.dishTypeRepository.delete(found.get());
        this.dishTypeRepository.flush();

        // DELETED
        return true;
    }
    
    @Override
    @Transactional
    public boolean deleteDietaryType(Long id) {

        Optional<DietaryType> found = this.dietaryTypeRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return false;
        }

        // CONFLICT
        if (this.recipeRepository.existsByDietaryTypeId(id)) {
            throw new ClassificationConflictException(
                    "Dietary type cannot be deleted because it is used by recipes.");
        }

        this.dietaryTypeRepository.delete(found.get());
        this.dietaryTypeRepository.flush();

        // DELETED
        return true;
    }
    
    @Override
    @Transactional
    public boolean deleteIngredientCategory(Long id) {

        Optional<IngredientCategory> found =
                this.ingredientCategoryRepository.findById(id);

        // NOT_FOUND
        if (found.isEmpty()) {
            return false;
        }

        this.ingredientCategoryRepository.delete(found.get());
        this.ingredientCategoryRepository.flush();

        // DELETED
        return true;
    }
    
    
    
    // SEARCH
    //____________________________________________________________________________________________
    
    @Override
    public Page<MealType> searchMealTypes(String search,int page,int size,String sortBy,String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);

        if (!"id".equals(sortBy)) {
            sort = sort.and(Sort.by("id"));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        // OK
        return this.mealTypeRepository.findByNameContainingIgnoreCase(search.trim(),pageable);
    }
    
    @Override
    public Page<DishType> searchDishTypes(String search, int page, int size, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        if (!"id".equals(sortBy)) {
            sort = sort.and(Sort.by("id"));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        // OK
        return this.dishTypeRepository.findByNameContainingIgnoreCase(search.trim(), pageable);
    }
    
    @Override
    public Page<DietaryType> searchDietaryTypes(String search, int page, int size, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        if (!"id".equals(sortBy)) {
            sort = sort.and(Sort.by("id"));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        // OK
        return this.dietaryTypeRepository.findByNameContainingIgnoreCase(search.trim(), pageable);
    }
    
    @Override
    public Page<IngredientCategory> searchIngredientCategories(String search, int page, int size,String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        if (!"id".equals(sortBy)) {
            sort = sort.and(Sort.by("id"));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        // OK
        return this.ingredientCategoryRepository.findByNameContainingIgnoreCase(search.trim(), pageable);
    }

}