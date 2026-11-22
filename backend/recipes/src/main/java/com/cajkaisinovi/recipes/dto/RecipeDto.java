package com.cajkaisinovi.recipes.dto;

import com.cajkaisinovi.recipes.domain.Recipe;
import java.time.LocalDateTime;

public class RecipeDto {

    private Long id;
    private String name;
    private String description;

    private Long authorId;
    private String authorUsername;

    private Long mealTypeId;
    private String mealTypeName;

    private Long dishTypeId;
    private String dishTypeName;

    private Long dietaryTypeId;
    private String dietaryTypeName;

    private int servings;
    private int preparationTime;
    private String mainPhotoUrl;
    private LocalDateTime creationDate;
    private String originStory;

    public RecipeDto(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.description = recipe.getDescription();

        this.authorId = recipe.getAuthor().getId();
        this.authorUsername = recipe.getAuthor().getUsername();

        this.mealTypeId = recipe.getMealType().getId();
        this.mealTypeName = recipe.getMealType().getName();

        this.dishTypeId = recipe.getDishType().getId();
        this.dishTypeName = recipe.getDishType().getName();

        this.dietaryTypeId = recipe.getDietaryType().getId();
        this.dietaryTypeName = recipe.getDietaryType().getName();

        this.servings = recipe.getServings();
        this.preparationTime = recipe.getPreparationTime();
        this.mainPhotoUrl = recipe.getMainPhotoUrl();
        this.creationDate = recipe.getCreationDate();
        this.originStory = recipe.getOriginStory();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public Long getMealTypeId() {
        return mealTypeId;
    }

    public String getMealTypeName() {
        return mealTypeName;
    }

    public Long getDishTypeId() {
        return dishTypeId;
    }

    public String getDishTypeName() {
        return dishTypeName;
    }

    public Long getDietaryTypeId() {
        return dietaryTypeId;
    }

    public String getDietaryTypeName() {
        return dietaryTypeName;
    }

    public int getServings() {
        return servings;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getOriginStory() {
        return originStory;
    }
}