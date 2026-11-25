package com.cajkaisinovi.recipes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

}